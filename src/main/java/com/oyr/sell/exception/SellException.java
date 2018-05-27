package com.oyr.sell.exception;

import com.oyr.sell.enums.ResultEnum;
import lombok.Getter;

/**
 * Create by 欧阳荣
 * 2018/3/13 14:09
 * 自定义异常类
 */
@Getter
public class SellException extends  RuntimeException{

    /** 状态码 */
    public Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
