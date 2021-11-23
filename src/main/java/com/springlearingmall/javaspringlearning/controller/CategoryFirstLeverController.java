package com.springlearingmall.javaspringlearning.controller;


import com.springlearingmall.javaspringlearning.entity.User;
import com.springlearingmall.javaspringlearning.service.CartService;
import com.springlearingmall.javaspringlearning.service.CategoryFirstLeverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LFR
 * @since 2021-10-26
 */
@Controller
@RequestMapping("/categoryFirstLever")
public class CategoryFirstLeverController {

    @Autowired
    private CategoryFirstLeverService categoryFirstLeverService;

    @Autowired
    private CartService cartService;

    @GetMapping("/categoryVOList")
    public ModelAndView categoryVOList(HttpSession session){
        ModelAndView modelAndView= new ModelAndView();
        modelAndView.setViewName("main");
        modelAndView.addObject("categoryVOList",categoryFirstLeverService.getAllCategoryVO());
        User user = (User) session.getAttribute("user");
        if(user == null)
        {
            modelAndView.addObject("cartList",new ArrayList<>());
        }else
        {modelAndView.addObject("cartList",cartService.findAllCartVOByUserId(user.getId()));}
        return modelAndView;

    }
}

