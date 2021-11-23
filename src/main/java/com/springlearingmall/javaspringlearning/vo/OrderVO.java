package com.springlearingmall.javaspringlearning.vo;

import lombok.Data;

import java.util.List;

@Data
public class OrderVO {
    private Integer id;
    private String loginName;
    private String serialnumber;
    private String addressName;
    private Float cost;
    private List<OrderDetailVO> orderDetailVOList;

    public OrderVO(Integer id, String loginName, String serialnumber, String addressName, Float cost) {
        this.id = id;
        this.loginName = loginName;
        this.serialnumber = serialnumber;
        this.addressName = addressName;
        this.cost = cost;
    }
}
