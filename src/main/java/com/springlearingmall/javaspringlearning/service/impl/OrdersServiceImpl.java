package com.springlearingmall.javaspringlearning.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springlearingmall.javaspringlearning.entity.*;
import com.springlearingmall.javaspringlearning.mapper.CartMapper;
import com.springlearingmall.javaspringlearning.mapper.OrderDetailMapper;
import com.springlearingmall.javaspringlearning.mapper.OrdersMapper;
import com.springlearingmall.javaspringlearning.mapper.UserAddressMapper;
import com.springlearingmall.javaspringlearning.service.OrdersService;
import com.springlearingmall.javaspringlearning.service.UserAddressService;
import com.springlearingmall.javaspringlearning.vo.OrderVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LFR
 * @since 2021-10-24
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private UserAddressMapper userAddressMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
@Autowired
private UserAddressService userAddressService;

    @Override
    public boolean save(Orders orders, User user, String selectedAddressId, Float cost) {


        Map<String, Object> map = new HashMap<>();
        map.put("id", selectedAddressId);
        List<UserAddress> orderList = userAddressMapper.selectByMap(map);
        UserAddress userAddress = orderList.get(0);
        orders.setAddressId(userAddress.getId());
        orders.setUserId(user.getId());
        orders.setCost(cost);
        String serialNumber = null;
        try {
            StringBuffer result = new StringBuffer();
            for (int i = 0; i < 32; i++) {
                result.append(Integer.toHexString(new Random().nextInt(16)));
            }
            serialNumber = result.toString().toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        orders.setSerialNumber(serialNumber);
        ordersMapper.insert(orders);
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_id", user.getId());
        List<Cart> cartList = cartMapper.selectList(wrapper);
        for (Cart cart : cartList) {
            OrderDetail orderDetail = new OrderDetail();
            BeanUtils.copyProperties(cart, orderDetail);
            orderDetail.setId(null);
            orderDetail.setOrderId(orders.getId());
            orderDetailMapper.insert(orderDetail);
        }
        cartMapper.delete(wrapper);
        return true;
    }

    @Override
    public List<OrderVO> findAllOrderVOByUserId(Integer id, User user) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_id", id);
        List<Orders> ordersList = ordersMapper.selectList(wrapper);
        //VO转换
       List<OrderVO> orderVOList = new ArrayList<>();
        for (Orders orders : ordersList) {
            OrderVO orderVO = new OrderVO();
            BeanUtils.copyProperties(orders,orderVO);
            orderVO.setLoginName(user.getLoginName());
            orderVOList.add(orderVO);
        }
        List<OrderVO> orderVOList = ordersList.stream()
                .map(e -> new OrderVO(
                        e.getId(),
                        user.getLoginName(),
                        e.getSerialNumber(),
                        userAddressService.,
                        e.getCost()
                )).collect(Collectors.toList());
        //封装OrderDetail
        for (OrderVO orderVO : orderVOList) {
            QueryWrapper wrapper1 = new QueryWrapper();
            wrapper1.eq("order_id", orderVO.getId());
            List<OrderDetail> orderDetailList = orderDetailMapper.selectList(wrapper1);
            List<OrderDetailVO> orderDetailVOList = new ArrayList<>();
            for (OrderDetail orderDetail : orderDetailList) {
                OrderDetailVO orderDetailVO = new OrderDetailVO();
                Product product = productMapper.selectById(orderDetail.getProductId());
                BeanUtils.copyProperties(product, orderDetailVO);
                BeanUtils.copyProperties(orderDetail, orderDetailVO);
                orderDetailVOList.add(orderDetailVO);
            }
            orderVO.setOrderDetailVOList(orderDetailVOList);
        }
        return orderVOList;
    }

}
