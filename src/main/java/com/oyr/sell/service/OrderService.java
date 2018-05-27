package com.oyr.sell.service;

import com.oyr.sell.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Create by 欧阳荣
 * 2018/3/12 20:44
 * 订单service
 */
public interface OrderService {

    /** 创建订单. */
    OrderDTO create(OrderDTO orderDTO);

    /** 查询单个订单. */
    OrderDTO findOne(String orderId);

    /** 查询订单列表. */
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

    /** 取消订单. */
    OrderDTO cancel(OrderDTO orderDTO);

    /** 完结订单. */
    OrderDTO finish(OrderDTO orderDTO);

    /** 支付订单. */
    OrderDTO paid(OrderDTO orderDTO);


}
