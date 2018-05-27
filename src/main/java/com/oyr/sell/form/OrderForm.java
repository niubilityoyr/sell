package com.oyr.sell.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Create by 欧阳荣
 * 2018/3/13 20:44
 */
@Data
public class OrderForm {

    /**
     * 买家姓名
     */
    @NotEmpty(message = "姓名必填")
    private String name;

    /** 买家手机 */
    @NotEmpty(message = "手机必填")
    private String phone;

    /** 买家地址 */
    @NotEmpty(message = "地址必填")
    private String address;

    /** 买家微信id */
    @NotEmpty(message = "微信id必填")
    private String openid;

    /** 购物车信息 */
    @NotEmpty(message = "购物车信息不能为空")
    private String items;

}
