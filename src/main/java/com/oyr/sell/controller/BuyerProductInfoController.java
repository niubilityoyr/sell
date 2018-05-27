package com.oyr.sell.controller;

import com.oyr.sell.dataobject.ProductCategory;
import com.oyr.sell.dataobject.ProductInfo;
import com.oyr.sell.service.CategoryService;
import com.oyr.sell.service.ProductInfoService;
import com.oyr.sell.utils.ResultVoUtils;
import com.oyr.sell.vo.ProductInfoVo;
import com.oyr.sell.vo.ProductVo;
import com.oyr.sell.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Create by 欧阳荣
 * 2018/3/12 15:39
 * 商品的扩展层
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductInfoController {

    @Autowired
    private ProductInfoService service;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/list")
    public ResultVo list(){
        //查找出所有的上架商品
        List<ProductInfo> upAll = service.findUpAll();

/*        //查出商品相关的类目
        List<Integer> categoryTypeList = new ArrayList();
        for (ProductInfo info : upAll) {
            categoryTypeList.add(info.getCategoryType());
        }*/
        //jdk1.8的操作，获取要查询类目的id
        List<Integer> categoryTypeList = upAll.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> productCategories = categoryService.findByCategoryTypeIn(categoryTypeList);

        //要展示的数据
        List list = new ArrayList();
        //数据构建
        for (ProductCategory productCategory : productCategories) {
            //循环类目
            ProductVo productVo = new ProductVo();
            BeanUtils.copyProperties(productCategory, productVo);
            List<ProductInfoVo> productInfoVoList = new ArrayList<>();
            for (ProductInfo info : upAll) {
                //循环商品
                if(info.getCategoryType().equals(productCategory.getCategoryType())) {
                     //属于当前类目
                     ProductInfoVo productInfoVo = new ProductInfoVo();
                     BeanUtils.copyProperties(info, productInfoVo);
                     productInfoVoList.add(productInfoVo);
                }
             }
            productVo.setProductInfoVoList(productInfoVoList);
            list.add(productVo);
        }
        return ResultVoUtils.ok(list);
    }

}
