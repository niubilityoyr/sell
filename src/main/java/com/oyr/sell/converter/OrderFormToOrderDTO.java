package com.oyr.sell.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.oyr.sell.dataobject.OrderDetail;
import com.oyr.sell.dto.OrderDTO;
import com.oyr.sell.enums.ResultEnum;
import com.oyr.sell.exception.SellException;
import com.oyr.sell.form.OrderForm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by 欧阳荣
 * 2018/3/13 21:09
 */
@Data
@Slf4j
public class OrderFormToOrderDTO {

    public static OrderDTO converter(OrderForm orderForm) {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            Gson gson = new Gson();
            orderDetailList =
                    gson.fromJson(orderForm.getItems(), new TypeToken< List<OrderDetail>>(){}.getType());
        }catch (Exception e){
            log.error("【创建订单】 订单创建失败！json参数不正确，json：{}", orderForm.getItems());
            throw  new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

}
