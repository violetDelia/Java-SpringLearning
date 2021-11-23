package com.springlearingmall.javaspringlearning.service.impl;

import com.springlearingmall.javaspringlearning.entity.Product;
import com.springlearingmall.javaspringlearning.mapper.ProductMapper;
import com.springlearingmall.javaspringlearning.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LFR
 * @since 2021-10-24
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    ProductService productService;

    @Override
    //二级分类下的商品列表
    public List<Product> findByParentId( Integer parentID) {
        Map<String,Object> map = new HashMap<>();
        map.put("parent_id",parentID);
        return productService.listByMap(map);
    }

    @Override
    //一级分类下的商品列表
    public List<Product> findByFirstLeverID(Integer firstLeverID) {
        Map<String,Object> map = new HashMap<>();
        map.put("first_lever_id",firstLeverID);
        return productService.listByMap(map);
    }
}
