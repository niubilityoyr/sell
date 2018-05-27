package com.oyr.sell.service;

import com.oyr.sell.dto.OrderDTO;

/**
 * Create by 欧阳荣
 * 2018/3/14 10:44
 */
public interface BuyerService {

    OrderDTO findOrderOne(String openid, String orderId);

    void cancel(String openid, String orderId);
}
