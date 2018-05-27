package com.oyr.sell.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Create by 欧阳荣
 * 2018/3/27 10:17
 * 微信公众号配置
 */
@Configuration  //这是一个配置文件
public class WechatMapConfig {

    @Autowired
    private WechatMpParameter wechatMpParameter;

    @Bean
    public WxMpService wxMpService(){
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }

    @Bean
    public WxMpConfigStorage wxMpConfigStorage(){
        WxMpInMemoryConfigStorage configStorage = new WxMpInMemoryConfigStorage();
        configStorage.setAppId(wechatMpParameter.getAppID());//设置appID
        configStorage.setSecret(wechatMpParameter.getAppsecret());//设置appsecret
        return configStorage;
    }

}
