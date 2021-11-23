package com.springlearingmall.javaspringlearning.service;

import com.springlearingmall.javaspringlearning.entity.Cart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.springlearingmall.javaspringlearning.vo.CartVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LFR
 * @since 2021-10-24
 */
public interface CartService extends IService<Cart> {
    public List<CartVO> findAllCartVOByUserId(Integer id);

}
