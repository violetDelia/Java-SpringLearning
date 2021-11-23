package com.springlearingmall.javaspringlearning.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springlearingmall.javaspringlearning.entity.User;
import com.springlearingmall.javaspringlearning.service.CartService;
import com.springlearingmall.javaspringlearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;
    /**
     * 登录
     *
     * @param loginName 用户名
     * @param password  密码
     * @param session
     * @return
     */
    @PostMapping("/login")
    public String login(String loginName, String password, HttpSession session) {
        //查询用户
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("login_name", loginName);
        /*password 检测不到*/
        wrapper.eq("password", password);
        User user = userService.getOne(wrapper);

        if (user == null) {
            //没查到
            return "login";
        } else {
            //查到了
            session.setAttribute("user", user);
            return "redirect:/categoryFirstLever/categoryVOList";
        }
    }

    /**
     * 登出
     *
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        //销毁当前user
        session.invalidate();
        return "login";
    }

    @PostMapping("/register")
    public String register(User user, Model model) {
        boolean registerSucceed = false;
        try {
            registerSucceed = userService.save(user);
        } catch (Exception e) {
            model.addAttribute("user_save_error", user.getLoginName() + "已存在！请重新输入！");
            return "register";
        }
        if (registerSucceed) return "login";
        return "register";
    }

    @GetMapping("/userInfo")
    public ModelAndView userInfo(HttpSession session){
        User user = (User) session.getAttribute("user");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userInfo");
        modelAndView.addObject("cartList",cartService.findAllCartVOByUserId(user.getId()));
        return modelAndView;
    }
}

