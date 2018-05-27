package com.oyr.sell.service;

import com.oyr.sell.dataobject.ProductInfo;
import com.oyr.sell.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Create by 欧阳荣
 * 2018/3/12 15:15
 * 商品信息service
 */
public interface ProductInfoService {

    //根据id查询信息
    ProductInfo findOne(String proId);

    //查询出所有上架的商品
    List<ProductInfo> findUpAll();

    //分页查询信息
    Page<ProductInfo> findAll(Pageable pageable);

    //保存数据
    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<CartDTO> cartDTOList);

    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);

}
