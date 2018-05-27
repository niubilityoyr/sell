package com.oyr.sell.repository;

import com.oyr.sell.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * Create by 欧阳荣
 * 2018/3/12 20:26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void save(){
        OrderMaster master = new OrderMaster();
        master.setOrderId("147258");
        master.setBuyerName("欧阳荣");
        master.setBuyerPhone("15216170956");
        master.setBuyerAddress("安远县");
        master.setBuyerOpenid("root");
        master.setOrderAmount(new BigDecimal(152));
        OrderMaster result = repository.save(master);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenid() {
        PageRequest request = new PageRequest(0, 2);
        Page page = repository.findByBuyerOpenid("root", request);
        Assert.assertNotEquals(0, page.getContent().size());
    }
}