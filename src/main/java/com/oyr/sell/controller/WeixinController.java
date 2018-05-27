package com.oyr.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Create by 欧阳荣
 * 2018/3/27 8:35
 */
@RequestMapping("/weixin")
@RestController
@Slf4j
public class WeixinController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code){
        log.info("进入auth方法");
        log.info("当前的code为:{}", code);
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxde9ab4be7455013b&secret=7e03e87680ce6852a8ac84f6ad43233a&code="+ code +"&grant_type=authorization_code";
        String str = restTemplate.getForObject(url, String.class);
        log.info("access_token为：{}", str);
    }

}
