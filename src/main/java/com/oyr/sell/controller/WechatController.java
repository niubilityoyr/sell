package com.oyr.sell.controller;

import com.oyr.sell.enums.ResultEnum;
import com.oyr.sell.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.net.URLEncoder;

/**
 * Create by 欧阳荣
 * 2018/3/27 10:13
 */
@Controller
@RequestMapping("/wechat")
@Slf4j
public class WechatController {

    @Resource
    private WxMpService wxMpService;

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl){
        //配置
        //调用方法
        String url = "http://2k7p99.natappfree.cc/sell/wechat/userInfo";
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_BASE, URLEncoder.encode(returnUrl));
        log.info("【微信网页授权】获取code，redisrecUrl：{}", redirectUrl);
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code, @RequestParam("state") String returnUrl){
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = null;
        try {
            //获取 openId
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
            String openId = wxMpOAuth2AccessToken.getOpenId();
            log.info("【微信网页授权】：openId：{}", openId);
            log.info("redirectUrl:{}", returnUrl + "?openid=" + openId);
            return "redirect:" + returnUrl + "?openid=" + openId;
        }catch (WxErrorException e){
            log.error("【微信网页授权】：{}", ResultEnum.WECHAT_MP_ERROR.getMsg());
            throw new SellException(ResultEnum.WECHAT_MP_ERROR);
        }
    }

}
