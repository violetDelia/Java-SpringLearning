package com.springlearingmall.javaspringlearning.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springlearingmall.javaspringlearning.entity.Cart;
import com.springlearingmall.javaspringlearning.entity.Product;
import com.springlearingmall.javaspringlearning.enums.ExceptionEnum;
import com.springlearingmall.javaspringlearning.exception.MyException;
import com.springlearingmall.javaspringlearning.mapper.CartMapper;
import com.springlearingmall.javaspringlearning.mapper.ProductMapper;
import com.springlearingmall.javaspringlearning.service.CartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springlearingmall.javaspringlearning.vo.CartVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LFR
 * @since 2021-10-24
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

    @Autowired
    CartMapper cartMapper;

    @Autowired
    ProductMapper productMapper;

    @Override
    public boolean save(Cart entity) {

        Product product= productMapper.selectById(entity.getProductId());
        Integer newStock = product.getStock()-entity.getQuantity();
        if(newStock < 0){
            throw new MyException(ExceptionEnum.STOCK_ERROR);
        }
        product.setStock(newStock);
        productMapper.updateById(product);
        return super.save(entity);
    }

    @Override
    public List<CartVO> findAllCartVOByUserId(Integer userId) {
        List<CartVO> cartVOList = new ArrayList<>();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_id",userId);
        List<Cart> cartList = cartMapper.selectList(wrapper);
        for (Cart cart : cartList) {
            CartVO cartVO = new CartVO();
            Product product = productMapper.selectById(cart.getProductId());
            BeanUtils.copyProperties(product,cartVO);
            BeanUtils.copyProperties(cart,cartVO);
            cartVOList.add(cartVO);
        }
        return cartVOList;
    }
}
