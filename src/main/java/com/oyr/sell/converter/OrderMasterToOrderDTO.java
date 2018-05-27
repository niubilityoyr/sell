package com.oyr.sell.converter;

import com.oyr.sell.dataobject.OrderMaster;
import com.oyr.sell.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Create by 欧阳荣
 * 2018/3/13 16:36
 * OrderMaster转换成OrderDTO
 */
public class OrderMasterToOrderDTO {

    public static OrderDTO converter(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> converter(List<OrderMaster> orderMasters){
        return orderMasters.stream().map(e -> converter(e)).collect(Collectors.toList());
    }

}
