package com.springlearingmall.javaspringlearning.service;

import com.springlearingmall.javaspringlearning.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import com.springlearingmall.javaspringlearning.entity.User;
import com.springlearingmall.javaspringlearning.vo.OrderVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LFR
 * @since 2021-10-24
 */
public interface OrdersService extends IService<Orders> {
    public boolean save(Orders orders, User user, String selectedAddressId, Float cost);
    public List<OrderVO> findAllOrderVOByUserId(Integer id, User user);
}
