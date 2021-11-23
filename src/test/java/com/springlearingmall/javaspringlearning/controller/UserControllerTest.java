package com.springlearingmall.javaspringlearning.controller;

import com.springlearingmall.javaspringlearning.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserControllerTest {

    @Autowired
    UserController userController;

    @Test
    public void test(){
//        User user = new User();
//        user.setEmail("123@test.com");
//        user.setPhone(123456);
//        user.setUserName("测试");
//        user.setPassword("test_pass");
//        user.setLoginName("test123");
//        userController.register(user);
    }
}