package com.oyr.sell.service.impl;

import com.oyr.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * Create by 欧阳荣
 * 2018/3/12 10:54
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl service;

    @Test
    public void findOne() {
        ProductCategory category = service.findOne(1);
        Assert.assertEquals(new Integer(1), category.getCategoryId());
    }

    @Test
    public void findAll() {
        List<ProductCategory> list = service.findAll();
        Assert.assertNotEquals(0, list.size());
    }

    @Test
    public void save() {
        ProductCategory category = new ProductCategory("女生最爱", 3);
        ProductCategory  productCategory = service.save(category);
        Assert.assertNotNull(productCategory);
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> categories = service.findByCategoryTypeIn(Arrays.asList(1, 2, 3, 4));
        Assert.assertNotEquals(0, categories.size());
    }
}