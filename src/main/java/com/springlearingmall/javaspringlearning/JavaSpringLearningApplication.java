package com.springlearingmall.javaspringlearning;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.springlearingmall.javaspringlearning.mapper")
public class JavaSpringLearningApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaSpringLearningApplication.class, args);
    }

}
