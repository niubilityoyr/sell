package com.oyr.sell.service;

import com.oyr.sell.dataobject.ProductCategory;

import java.util.List;

/**
 * Create by 欧阳荣
 * 2018/3/12 10:47
 * 商品目录service
 */
public interface CategoryService {

    /** 根据id查询数据 */
    ProductCategory findOne(Integer categoryId);

    /** 查询所有数据 */
    List<ProductCategory> findAll();

    /** 保存一条数据 */
    ProductCategory save(ProductCategory productCategory);

    /** 根据CategoryType来查询 */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
