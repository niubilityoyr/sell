package com.oyr.sell.repository;

import com.oyr.sell.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Create by 欧阳荣
 * 2018/3/12 14:52
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    //根据商品状态查询商品
    List<ProductInfo> findByProductStatus(Integer productStatus);

}
