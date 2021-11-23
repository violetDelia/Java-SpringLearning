package com.springlearingmall.javaspringlearning.controller;


import com.springlearingmall.javaspringlearning.entity.Orders;
import com.springlearingmall.javaspringlearning.entity.User;
import com.springlearingmall.javaspringlearning.service.CartService;
import com.springlearingmall.javaspringlearning.service.OrdersService;
import com.springlearingmall.javaspringlearning.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LFR
 * @since 2021-10-24
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    OrdersService ordersService;
    @Autowired
    private CartService cartService;
    @Autowired
    private UserAddressService userAddressService;
    @PostMapping("submitOrder")
    public ModelAndView submitOrder(Orders orders, String userAddressId, Float cost, HttpSession session){
        User user =(User) session.getAttribute("user");
        ordersService.save(orders,user,userAddressId,cost);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("submitOrder");
        modelAndView.addObject("cartList",cartService.findAllCartVOByUserId(user.getId()));
        modelAndView.addObject("orders",orders);
        modelAndView.addObject("address",userAddressService.getById(orders.getAddressId()));
        modelAndView.addObject("user",user);
        return modelAndView;
    }

    @GetMapping("/list")
    public ModelAndView list(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("orderList");
        User user = (User) session.getAttribute("user");
        modelAndView.addObject("list",ordersService.findAllOrderVOByUserId(user.getId(),user));
        modelAndView.addObject("cartList",cartService.findAllCartVOByUserId(user.getId()));
        return modelAndView;
    }


}

