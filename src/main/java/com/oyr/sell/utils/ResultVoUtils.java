package com.oyr.sell.utils;

import com.oyr.sell.vo.ResultVo;

/**
 * Create by 欧阳荣
 * 2018/3/12 15:44
 */
public class ResultVoUtils {

    //成功，带参数
    public static ResultVo ok(Object object){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(0);
        resultVo.setMsg("成功");
        resultVo.setData(object);
        return resultVo;
    }

    //成功，不带参
    public static ResultVo ok(){
        return ok(null);
    }

    //error
    public static ResultVo error(Integer code, String msg){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(code);
        resultVo.setMsg(msg);
        return resultVo;
    }

}
