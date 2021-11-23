package com.springlearingmall.javaspringlearning.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springlearingmall.javaspringlearning.entity.CategoryFirstLever;
import com.springlearingmall.javaspringlearning.entity.CategorySecondLever;
import com.springlearingmall.javaspringlearning.entity.Product;
import com.springlearingmall.javaspringlearning.mapper.CategoryFirstLeverMapper;
import com.springlearingmall.javaspringlearning.mapper.CategorySecondLeverMapper;
import com.springlearingmall.javaspringlearning.mapper.ProductMapper;
import com.springlearingmall.javaspringlearning.service.CategoryFirstLeverService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springlearingmall.javaspringlearning.vo.CategoryVO;
import com.springlearingmall.javaspringlearning.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LFR
 * @since 2021-10-26
 */
@Service
public class CategoryFirstLeverServiceImpl extends ServiceImpl<CategoryFirstLeverMapper, CategoryFirstLever> implements CategoryFirstLeverService {
    @Autowired
CategoryFirstLeverMapper categoryFirstLeverMapper;

    @Autowired
    CategorySecondLeverMapper categorySecondLeverMapper;

    @Autowired
    ProductMapper productMapper;
    @Override
    public List<CategoryVO> getAllCategoryVO() {
        //获取一级分类列表
        List<CategoryFirstLever> categoryFirstLeverList =categoryFirstLeverMapper.selectList(null);

        List<CategoryVO> categoryVOList = new ArrayList<>();
        //一级视图
        for(CategoryFirstLever categoryFirstLever:categoryFirstLeverList){
            CategoryVO categoryVO = new CategoryVO();
            categoryVO.setId(categoryFirstLever.getId());
            categoryVO.setName(categoryFirstLever.getName());
            //图片信息
            categoryVO.setBannerImage(categoryFirstLever.getBannerImage());
            categoryVO.setRightImage(categoryFirstLever.getRightImage());
            categoryVOList.add(categoryVO);
        }
        for (int i = 0; i < categoryVOList.size(); i++) {
            //给一级视图载入商品列表
            QueryWrapper queryWrapperProductList = new QueryWrapper();
            queryWrapperProductList.eq("first_lever_id",categoryVOList.get(i).getId());
            List<Product> productList = productMapper.selectList(queryWrapperProductList);
            List<ProductVO> productVOList = productList.stream()
                    .map(e -> new ProductVO(
                            e.getId(),
                            e.getName(),
                            e.getPrice(),
                            e.getImageFileName()
                    )).collect(Collectors.toList());
            categoryVOList.get(i).setProductVOList(productVOList);
        }
        //二级视图
        for(CategoryVO categoryVOLeverOne: categoryVOList){
            QueryWrapper queryWrapperLeverTwoVO = new QueryWrapper();
            queryWrapperLeverTwoVO.eq("parent_id",categoryVOLeverOne.getId());
            List<CategorySecondLever> categoryListLeverTwo = categorySecondLeverMapper.selectList(queryWrapperLeverTwoVO);
            List<CategoryVO> categoryVOListLeverTwo = categoryListLeverTwo.stream().map(e-> new CategoryVO(e.getId(),e.getName())).collect(Collectors.toList());
            categoryVOLeverOne.setSubCategory(categoryVOListLeverTwo);
            //三级视图
            for(CategoryVO categoryVOLeverTwo:categoryVOListLeverTwo){
                QueryWrapper queryWrapperLeverThreeVO = new QueryWrapper();
                queryWrapperLeverThreeVO.eq("parent_id",categoryVOLeverTwo.getId());
                List<Product> categoryListLeverThree = productMapper.selectList(queryWrapperLeverThreeVO);
                List<CategoryVO> categoryVOListLeverThree = categoryListLeverThree.stream().map(e->new CategoryVO(e.getId(),e.getName())).collect(Collectors.toList());
                categoryVOLeverTwo.setSubCategory(categoryVOListLeverThree);
            }
        }
        return categoryVOList;
    }
}
