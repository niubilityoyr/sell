package com.oyr.sell.vo;

import lombok.Data;

/**
 * Create by 欧阳荣
 * 2018/3/12 15:42
 */
@Data
public class ResultVo<T> {

    /** 状态码 */
    private Integer code;

    /** 信息 */
    private String msg;

    /** 内容 */
    private T data;

}
