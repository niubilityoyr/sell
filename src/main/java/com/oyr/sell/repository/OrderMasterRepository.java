package com.oyr.sell.repository;

import com.oyr.sell.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Create by 欧阳荣
 * 2018/3/12 20:19
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

    //根据买家微信id获取信息，并分页
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

}
