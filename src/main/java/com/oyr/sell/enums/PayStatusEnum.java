package com.oyr.sell.enums;

import lombok.Getter;

/**
 * Create by 欧阳荣
 * 2018/3/12 20:17
 */
@Getter
public enum PayStatusEnum {

    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功");

    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
