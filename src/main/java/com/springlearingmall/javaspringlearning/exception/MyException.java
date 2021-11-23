package com.springlearingmall.javaspringlearning.exception;

import com.springlearingmall.javaspringlearning.enums.ExceptionEnum;
/**
 * unchecked 不用去处理，交给JVM去处理，继承 RuntimeException
 * checked，需要自己处理，继承 Exception
 */
public class MyException extends RuntimeException {
    public MyException(String error) {
        super(error);
    }
    public MyException(ExceptionEnum resultEnum) {
        super(resultEnum.getMessage());
    }
}
