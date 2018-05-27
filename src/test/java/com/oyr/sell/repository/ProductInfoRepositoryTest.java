package com.oyr.sell.repository;

import com.oyr.sell.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * Create by 欧阳荣
 * 2018/3/12 14:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {
    
    @Autowired
    private ProductInfoRepository repository;
    
    @Test
    public void save(){
        ProductInfo info = new ProductInfo();
        info.setProductId("1234");
        info.setProductName("红烧豆腐");
        info.setProductPrice(new BigDecimal(13.5));
        info.setProductStock(100);
        info.setProductDescription("这是很好吃的豆腐");
        info.setProductIcon("www.xxx.jpg");
        info.setProductStatus(0);
        info.setCategoryType(1);
        ProductInfo result = repository.save(info);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByProductStatus() {
        List<ProductInfo> list = repository.findByProductStatus(0);
        Assert.assertNotEquals(0, list.size());
    }
}