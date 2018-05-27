package com.oyr.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Create by 欧阳荣
 * 2018/3/27 10:26
 */
@Component
@Data
@ConfigurationProperties(prefix = "wechat")
public class WechatMpParameter {

    private String appID;

    private String appsecret;

}
