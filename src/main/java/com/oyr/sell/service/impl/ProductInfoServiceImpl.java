package com.oyr.sell.service.impl;

import com.oyr.sell.dataobject.ProductInfo;
import com.oyr.sell.dto.CartDTO;
import com.oyr.sell.enums.ProductStatusEnum;
import com.oyr.sell.enums.ResultEnum;
import com.oyr.sell.exception.SellException;
import com.oyr.sell.repository.ProductInfoRepository;
import com.oyr.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Create by 欧阳荣
 * 2018/3/12 15:16
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoRepository repository;

    @Override
    public ProductInfo findOne(String proId) {
        return repository.findOne(proId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cart : cartDTOList) {
            ProductInfo productInfo = repository.findOne(cart.getProductId());
            Integer result = productInfo.getProductStock()+cart.getProductQuantity();
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cart : cartDTOList) {
            ProductInfo productInfo = repository.findOne(cart.getProductId());
            Integer result = productInfo.getProductStock()-cart.getProductQuantity();
            if(result<0){
                //库存数量不足
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }

}
