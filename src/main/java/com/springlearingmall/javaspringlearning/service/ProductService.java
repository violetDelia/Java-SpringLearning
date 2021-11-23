package com.springlearingmall.javaspringlearning.service;

import com.springlearingmall.javaspringlearning.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LFR
 * @since 2021-10-24
 */
public interface ProductService extends IService<Product> {

 List<Product> findByParentId(Integer parentID);

 List<Product> findByFirstLeverID(Integer firstLeverID);
}
