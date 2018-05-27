package com.oyr.sell;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {
    @Test
    public void test1(){
        String name = "oyr";
        String password = "o123456";
        log.debug("debug---------------------");
        log.info("info---------------name:"+name+"\tpassword="+password);
        log.info("info---------------name:{},password:{}", name, password);
        log.error("error----------------");
        log.warn("warn--------------------");
    }

}
