package com.oyr.sell.handle;

import com.oyr.sell.exception.SellException;
import com.oyr.sell.utils.ResultVoUtils;
import com.oyr.sell.vo.ResultVo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Create by 欧阳荣
 * 2018/3/13 14:13
 */
@ControllerAdvice   //这是一个全局异常处理类
public class MyExceptionHandle {

    @ExceptionHandler(SellException.class)
    @ResponseBody
    public ResultVo handle(SellException e){
        return ResultVoUtils.error(e.getCode(), e.getMessage());
    }

}
