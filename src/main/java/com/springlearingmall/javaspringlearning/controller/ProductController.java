package com.springlearingmall.javaspringlearning.controller;


import com.springlearingmall.javaspringlearning.entity.User;
import com.springlearingmall.javaspringlearning.service.CartService;
import com.springlearingmall.javaspringlearning.service.CategoryFirstLeverService;
import com.springlearingmall.javaspringlearning.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
 * @since 2021-10-24
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryFirstLeverService categoryFirstLeverService;

    @Autowired
    CartService cartService;
    @GetMapping("/secondLeverList/{id}")
    //二级分类下的页面
    public ModelAndView secondLeverList(@PathVariable ("id") Integer parentID ,HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productList");
        modelAndView.addObject("productList",productService.findByParentId(parentID));
        modelAndView.addObject("categoryVOList",categoryFirstLeverService.getAllCategoryVO());
        User user = (User) session.getAttribute("user");
        if(user == null)
        {
            modelAndView.addObject("cartList",new ArrayList<>());
        }else
        {modelAndView.addObject("cartList",cartService.findAllCartVOByUserId(user.getId()));}
        return  modelAndView;

    }

    @GetMapping("/firstLeverList/{id}")
    //一级分类下的页面
    public ModelAndView firstLeverList(@PathVariable ("id") Integer firstLeverID,HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productList");
        modelAndView.addObject("productList",productService.findByFirstLeverID(firstLeverID));
        modelAndView.addObject("categoryVOList",categoryFirstLeverService.getAllCategoryVO());
        User user = (User) session.getAttribute("user");
        if(user == null)
        {
            modelAndView.addObject("cartList",new ArrayList<>());
        }else
        {modelAndView.addObject("cartList",cartService.findAllCartVOByUserId(user.getId()));}
        return  modelAndView;

    }

    @GetMapping("/product={id}")
    //商品详情页
    public  ModelAndView findById(@PathVariable("id") Integer productId,HttpSession session){
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("productDetail");
                modelAndView.addObject("product",productService.getById(productId));
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

