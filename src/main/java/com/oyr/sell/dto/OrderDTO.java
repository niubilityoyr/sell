package com.oyr.sell.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.oyr.sell.dataobject.OrderDetail;
import com.oyr.sell.enums.OrderStatusEnum;
import com.oyr.sell.enums.PayStatusEnum;
import com.oyr.sell.utils.serialize.DateToLongSerialize;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Create by 欧阳荣
 * 2018/3/13 11:45
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)  //只包含非空的属性
public class OrderDTO {

    private String orderId;

    /** 买家名字 */
    private String buyerName;

    /** 买家电话 */
    private String buyerPhone;

    /** 买家地址 */
    private String buyerAddress;

    /** 买家微信openid */
    private String buyerOpenid;

    /** 订单总金额 */
    private BigDecimal orderAmount;

    /** 订单状态, 默认为新下单 0:新下单 */
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /** 支付状态, 默认未支付 0:未支付 */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    /** 创建时间 */
    @JsonSerialize(using = DateToLongSerialize.class) //序列化当前列
    private Date createTime;

    /** 修改时间 */
    @JsonSerialize(using = DateToLongSerialize.class)
    private Date updateTime;

    private List<OrderDetail> orderDetailList;

}
