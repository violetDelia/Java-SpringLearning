package com.springlearingmall.javaspringlearning.vo;

import lombok.Data;

@Data
public class OrderDetailVO {
    private String name;
    private String imageFileName;
    private Integer quantity;
    private Float price;
    private Float cost;
}
