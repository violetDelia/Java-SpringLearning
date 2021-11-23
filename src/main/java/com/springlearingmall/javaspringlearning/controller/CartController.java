package com.springlearingmall.javaspringlearning.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springlearingmall.javaspringlearning.entity.Cart;
import com.springlearingmall.javaspringlearning.entity.Product;
import com.springlearingmall.javaspringlearning.entity.User;
import com.springlearingmall.javaspringlearning.enums.ExceptionEnum;
import com.springlearingmall.javaspringlearning.exception.MyException;
import com.springlearingmall.javaspringlearning.service.CartService;
import com.springlearingmall.javaspringlearning.service.ProductService;
import com.springlearingmall.javaspringlearning.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author LFR
 * @since 2021-10-24
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;
    @Autowired
    ProductService productService;
    @Autowired
    UserAddressService userAddressService;

    @GetMapping("/add/{productId}/{price}/{quantity}")
    public String add(
            @PathVariable("productId") Integer productId,
            @PathVariable("price") Float price,
            @PathVariable("quantity") Integer quantity,
            HttpSession session
    ) {
        Cart cart = new Cart();
        cart.setProductId(productId);
        cart.setQuantity(quantity);
        cart.setCost(price * quantity);
        User user = (User) session.getAttribute("user");
        cart.setUserId(user.getId());
        try {
            if (cartService.save(cart)) {
                return "redirect:/cart/cartList";
            }
        } catch (Exception e) {
            return "redirect:/categoryFirstLever/categoryVOList";
        }
        return null;
    }

    @GetMapping("/cartList")
    public ModelAndView findAllCart(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cartDetail");
        User user = (User) session.getAttribute("user");
        modelAndView.addObject("cartList", cartService.findAllCartVOByUserId(user.getId()));
        return modelAndView;
    }

    @GetMapping("/removeCart/{id}")
    public String remoteCart(@PathVariable("id") Integer id) {
        cartService.removeById(id);
        return "redirect:/cart/cartList";
    }

    @GetMapping("/confirmOrder")
    public ModelAndView conformOeder(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("confirmOrder");
        User user = (User) session.getAttribute("user");
        modelAndView.addObject("cartList", cartService.findAllCartVOByUserId(user.getId()));
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_id", user.getId());
        modelAndView.addObject("addressList", userAddressService.list(wrapper));
        return modelAndView;
    }

    @GetMapping("/updateCart/{id}/{quantity}/{cost}")
    @ResponseBody
    public String updateCart(@PathVariable("id") Integer id,
                             @PathVariable("quantity") Integer quantity,
                             @PathVariable("cost") Float cost) {
        Cart cart = cartService.getById(id);
        Integer quantityChange = quantity - cart.getQuantity();
        cart.setQuantity(quantity);
        Product product = productService.getById(cart.getProductId());
        Integer newProductStock = product.getStock() - quantityChange;
        if (newProductStock < 0)
            throw new MyException(ExceptionEnum.STOCK_ERROR);
        product.setStock(newProductStock);
        if (!productService.updateById(product))
            return "fail";
        if (cartService.updateById(cart)) {
            return "success";
        } else {
            return "fail";
        }
    }


}

