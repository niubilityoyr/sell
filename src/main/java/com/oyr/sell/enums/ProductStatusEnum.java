package com.oyr.sell.enums;

import lombok.Getter;

/**
 * Create by 欧阳荣
 * 2018/3/12 19:11
 */
@Getter
public enum ProductStatusEnum {

    UP(0,"在架"),
    DOWN(1,"下架");

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
