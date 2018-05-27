package com.oyr.sell.repository;

import com.oyr.sell.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Create by 欧阳荣
 * 2018/3/12 20:20
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    //根据订单id获取订单详情
    List<OrderDetail> findByOrderId(String orderId);

}
