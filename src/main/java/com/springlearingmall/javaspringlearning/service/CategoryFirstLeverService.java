package com.springlearingmall.javaspringlearning.service;

import com.springlearingmall.javaspringlearning.entity.CategoryFirstLever;
import com.baomidou.mybatisplus.extension.service.IService;
import com.springlearingmall.javaspringlearning.vo.CategoryVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LFR
 * @since 2021-10-26
 */
public interface CategoryFirstLeverService extends IService<CategoryFirstLever> {
    public List<CategoryVO> getAllCategoryVO();
}
