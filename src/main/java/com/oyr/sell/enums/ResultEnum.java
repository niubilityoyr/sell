package com.oyr.sell.enums;

import lombok.Getter;

/**
 * Create by 欧阳荣
 * 2018/3/13 14:25
 */
@Getter
public enum  ResultEnum {

    PARAM_ERROR(1, "参数不正确"),
    CART_EMPTY(2, "购物车为空"),
    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"商品库存不正确"),
    ORDER_NOT_ERROR(12, "订单不存在"),
    ORDERDETAIL_NOT_ERROR(13, "订单不存在"),
    ORDER_STATUS_ERROR(14, "订单状态不正确"),
    ORDER_DETAIL_EMPTY(15, "订单中无商品"),
    ORDER_UPDATE_LOSE(16, "订单更新失败"),
    ORDER_PAY_STATUS_ERROR(18, "订单支付状态不正确"),
    ORDER_BUYER_ERROR(19, "此订单不属于当前用户"),

    WECHAT_MP_ERROR(20,"微信公众号方面错误"),
    ;

    /** 状态码 */
    private Integer code;
    /** 信息 */
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
