package com.springlearingmall.javaspringlearning.service.impl;

import com.springlearingmall.javaspringlearning.entity.User;
import com.springlearingmall.javaspringlearning.mapper.UserMapper;
import com.springlearingmall.javaspringlearning.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LFR
 * @since 2021-10-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
