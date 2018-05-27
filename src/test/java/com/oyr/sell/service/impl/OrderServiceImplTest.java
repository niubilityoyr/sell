package com.oyr.sell.service.impl;

import com.oyr.sell.dataobject.OrderDetail;
import com.oyr.sell.dto.OrderDTO;
import com.oyr.sell.enums.OrderStatusEnum;
import com.oyr.sell.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by 欧阳荣
 * 2018/3/13 15:35
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final String BUYER_OPENID="110110";

    private final String ORDER_ID="1520927222738412921";

    @Test
    @Transactional
    public void create() throws Exception {
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setBuyerName("欧阳荣");
        orderDTO.setBuyerAddress("宏图");
        orderDTO.setBuyerPhone("15216170956");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        //购物车
        List<OrderDetail> orderDetailList=new ArrayList<>();

        OrderDetail ol=new OrderDetail();
        ol.setProductId("1234");
        ol.setProductQuantity(2);

        OrderDetail o2=new OrderDetail();
        o2.setProductId("123456");
        o2.setProductQuantity(2);

        orderDetailList.add(ol);
        orderDetailList.add(o2);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result=orderService.create(orderDTO);
        Assert.assertNotNull(result.getOrderId());
    }

    @Test
    public void findOne() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        log.info("查询出的信息：{}", orderDTO);
    }

    @Test
    public void findList() {
        PageRequest request = new PageRequest(0, 2);
        Page<OrderDTO> page = orderService.findList(BUYER_OPENID, request);
        Assert.assertNotEquals(0, page.getTotalElements());
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO dto = orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), dto.getOrderStatus());
    }

    @Test
    public void finish() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO dto = orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(), dto.getOrderStatus());
    }

    @Test
    public void paid() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO dto = orderService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), dto.getPayStatus());
    }
}