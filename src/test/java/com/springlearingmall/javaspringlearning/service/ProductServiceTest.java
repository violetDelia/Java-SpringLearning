package com.springlearingmall.javaspringlearning.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {
    @Autowired
    ProductService productService;

    @Test
    public void test(){
//        Map<String,Object> map = new HashMap<>();
//        map.put("parent_id",1);
//        productService.listByMap(map).forEach(System.out::println);
    }
}