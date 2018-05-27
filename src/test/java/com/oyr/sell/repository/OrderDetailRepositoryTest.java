package com.oyr.sell.repository;

import com.oyr.sell.dataobject.OrderDetail;
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
 * 2018/3/12 20:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;


    @Test
    public void save(){
        OrderDetail detail = new OrderDetail();
        detail.setDetailId("123456");
        detail.setOrderId("123456");
        detail.setProductId("1234");
        detail.setProductName("红烧豆腐");
        detail.setProductPrice(new BigDecimal(13.5));
        detail.setProductQuantity(1);
        detail.setProductIcon("www.hsdf.jpg");
        OrderDetail result = repository.save(detail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> detailList = repository.findByOrderId("123456");
        Assert.assertNotEquals(0, detailList.size());
    }
}