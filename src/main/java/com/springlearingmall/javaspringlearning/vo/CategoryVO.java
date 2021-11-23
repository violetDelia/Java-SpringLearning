package com.springlearingmall.javaspringlearning.vo;

import lombok.Data;

import java.util.List;

@Data
public class CategoryVO {
    private Integer id;

    private String name;

    private List<CategoryVO> subCategory;

    //边栏图片名
    private  String bannerImage;

    //顶栏图片名
    private  String rightImage;

    //对应的商品列表
    private List<ProductVO> productVOList;

    public CategoryVO() {
    }
    public CategoryVO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
