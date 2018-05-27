package com.oyr.sell.service.impl;

import com.oyr.sell.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * Create by 欧阳荣
 * 2018/3/12 15:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoServiceImpl service;

    @Test
    public void findOne() {
        ProductInfo info = service.findOne("1234");
        Assert.assertEquals("1234", info.getProductId());
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> upAll = service.findUpAll();
        Assert.assertNotEquals(0, upAll.size());
    }

    @Test
    public void findAll() {
        PageRequest pageRequest = new PageRequest(0, 2);
        Page<ProductInfo> all = service.findAll(pageRequest);
        System.out.println(all.getTotalElements());
    }

    @Test
    public void save() {
        ProductInfo info = new ProductInfo();
        info.setProductId("123456");
        info.setProductName("盐晕鸡");
        info.setProductPrice(new BigDecimal(100.5));
        info.setProductStock(100);
        info.setProductDescription("这是很好吃的鸡哦");
        info.setProductIcon("www.ji.jpg");
        info.setProductStatus(0);
        info.setCategoryType(3);
        ProductInfo result = service.save(info);
        Assert.assertNotNull(result);
    }
}