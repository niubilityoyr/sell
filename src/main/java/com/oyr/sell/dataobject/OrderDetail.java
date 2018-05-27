package com.oyr.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Create by 欧阳荣
 * 2018/3/12 19:50
 * 订单描述
 */
@Entity
@DynamicUpdate
@Data
public class OrderDetail {

    @Id
    private String detailId;

    /** 订单id */
    private String orderId;

    /** 商品id */
    private String productId;

    /** 商品名称 */
    private String productName;

    /** 当前价格,单位分 */
    private BigDecimal productPrice;

    /** 数量 */
    private Integer productQuantity;

    /** 小图 */
    private String productIcon;

    private Date createTime;

    private Date updateTime;
}
