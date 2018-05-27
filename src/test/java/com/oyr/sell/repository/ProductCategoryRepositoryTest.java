package com.oyr.sell.repository;

import com.oyr.sell.dataobject.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findOne(){
        ProductCategory category = repository.findOne(1);
        System.out.println(category);
    }

    @Test
    public void save(){
        ProductCategory productCategory = new ProductCategory("男生最爱", 1);
        repository.save(productCategory);
    }

    @Test
    public void update(){
        ProductCategory category = repository.findOne(2);
        System.out.println(category);
        category.setCategoryName("女生最爱");
        repository.save(category);
    }

    @Test
    public void findByCategoryTypeIn(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        List<ProductCategory> categories = repository.findByCategoryTypeIn(list);
        for (ProductCategory productCategory : categories){
            System.out.println(categories);
        }
    }

}
