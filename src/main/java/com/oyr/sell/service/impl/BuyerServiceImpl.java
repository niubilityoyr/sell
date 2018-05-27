package com.oyr.sell.service.impl;

import com.oyr.sell.dto.OrderDTO;
import com.oyr.sell.enums.ResultEnum;
import com.oyr.sell.exception.SellException;
import com.oyr.sell.service.BuyerService;
import com.oyr.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by 欧阳荣
 * 2018/3/14 10:49
 * 买家service
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    /** 查询一条订单 */
    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return findOrderExtract(openid, orderId);
    }

    /** 取消订单 */
    public void cancel(String openid, String orderId) {
        OrderDTO orderDTO = findOrderExtract(openid, orderId);
        orderService.cancel(orderDTO);
    }

    public OrderDTO findOrderExtract(String openid, String orderId){
        OrderDTO orderDTO = orderService.findOne(orderId);
        if(orderDTO==null){
            //订单信息为空
            log.error("【查询订单】 当前订单不存在");
            throw new SellException(ResultEnum.ORDER_NOT_ERROR);
        }

        if(!openid.equalsIgnoreCase(orderDTO.getBuyerOpenid())){
            //订单不属于当前用户
            log.error("【查询订单】 此订单不属于当前用户");
            throw new SellException(ResultEnum.ORDER_BUYER_ERROR);
        }

        return orderDTO;
    }
}
