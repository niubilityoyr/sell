package com.oyr.sell.dto;

import lombok.Data;

/**
 * Create by 欧阳荣
 * 2018/3/13 15:21
 */
@Data
public class CartDTO {

    /** 商品id */
    private String productId;

    /** 购买数量 */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }

    public CartDTO() {

    }
}
