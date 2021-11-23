package com.springlearingmall.javaspringlearning.enums;

import lombok.Getter;

@Getter
public enum ExceptionEnum {
    STOCK_ERROR(1,"库存不足");

    private Integer code;
    private String message;

    ExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
