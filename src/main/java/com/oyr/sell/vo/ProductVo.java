package com.oyr.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Create by 欧阳荣
 * 2018/3/12 15:53
 */
@Data
public class ProductVo {

    @JsonProperty("name")   //转换成json时name变成指定的值
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVo> productInfoVoList;

}
