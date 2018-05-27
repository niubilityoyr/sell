package com.oyr.sell.service.impl;

import com.oyr.sell.converter.OrderMasterToOrderDTO;
import com.oyr.sell.dataobject.OrderDetail;
import com.oyr.sell.dataobject.OrderMaster;
import com.oyr.sell.dataobject.ProductInfo;
import com.oyr.sell.dto.CartDTO;
import com.oyr.sell.dto.OrderDTO;
import com.oyr.sell.enums.OrderStatusEnum;
import com.oyr.sell.enums.PayStatusEnum;
import com.oyr.sell.enums.ResultEnum;
import com.oyr.sell.exception.SellException;
import com.oyr.sell.repository.OrderDetailRepository;
import com.oyr.sell.repository.OrderMasterRepository;
import com.oyr.sell.service.OrderService;
import com.oyr.sell.service.ProductInfoService;
import com.oyr.sell.utils.KeyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Create by 欧阳荣
 * 2018/3/13 11:49
 * order的service
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoService service;

    @Autowired
    private OrderDetailRepository detailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    @Transactional
    public synchronized OrderDTO create(OrderDTO orderDTO) {

        //生成订单id
        String orderId = KeyUtils.createKey();

        //定义总价
        BigDecimal totalPrice = new BigDecimal(0);

        //1：查询商品
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            //获取商品信息
            ProductInfo productInfo = service.findOne(orderDetail.getProductId());
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            //2：计算总价
            totalPrice = productInfo.getProductPrice().
                    multiply(new BigDecimal(orderDetail.getProductQuantity())).
                    add(totalPrice);

            //3：写入数据库（订单描述）
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetail.setOrderId(orderId);
            orderDetail.setDetailId(KeyUtils.createKey());
            detailRepository.save(orderDetail);
        }

        //3：写入数据库（订单信息）
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setOrderAmount(totalPrice);
        orderMasterRepository.save(orderMaster);

        //4：修改库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().
                map(e -> new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        service.decreaseStock(cartDTOList);
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if(orderMaster==null){
            //订单不存在
            throw new SellException(ResultEnum.ORDER_NOT_ERROR);
        }
        List<OrderDetail> orderDetails = detailRepository.findByOrderId(orderId);
        if(orderDetails==null){
            //订单详情不存在
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_ERROR);
        }
        OrderDTO orderDTO = OrderMasterToOrderDTO.converter(orderMaster);
        orderDTO.setOrderDetailList(orderDetails);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        //查询出数据
        Page<OrderMaster> page = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        //转换数据
        return new PageImpl<OrderDTO>(OrderMasterToOrderDTO.converter(page.getContent()), pageable, page.getTotalElements());
    }

    @Override
    /** 取消订单 */
    public OrderDTO cancel(OrderDTO orderDTO) {
        //判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("[取消订单] 订单状态不正确,orderId：{}, orderstatus：{}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw  new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //修改订单
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if(updateResult == null){
            log.error("[取消订单] 更新失败！orderId：{}", updateResult.getOrderId());
            throw new SellException(ResultEnum.ORDER_UPDATE_LOSE);
        }

        //返回库存
        List<OrderDetail> orderDetails = orderDTO.getOrderDetailList();
        if(orderDetails == null){
            log.error("[取消订单] 订单中无商品");
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> list = orderDetails.stream().map(e -> new CartDTO(e.getProductId(), e.getProductQuantity())).
                collect(Collectors.toList());
        service.increaseStock(list);

        //返回付款
        if(orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())){
            //TODO
        }

        return orderDTO;
    }

    @Override
    /** 完结订单 */
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("[完结订单] 订单状态不正确,orderId：{}, orderstatus：{}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw  new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //修改订单
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if(updateResult == null){
            log.error("[完结订单] 更新失败！orderId：{}", updateResult.getOrderId());
            throw new SellException(ResultEnum.ORDER_UPDATE_LOSE);
        }
        return orderDTO;
    }

    @Override
    /** 订单支付 */
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("[订单支付] 订单状态不正确,orderId：{}, orderstatus：{}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw  new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //判断支付状态
        if(!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            log.error("[订单支付] 订单支付不正确,orderId：{}, payStatus：{}", orderDTO.getOrderId(), orderDTO.getPayStatus());
            throw  new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }

        //修改订单
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if(updateResult == null){
            log.error("[订单支付] 更新失败！orderId：{}", updateResult.getOrderId());
            throw new SellException(ResultEnum.ORDER_UPDATE_LOSE);
        }

        return orderDTO;
    }
}
