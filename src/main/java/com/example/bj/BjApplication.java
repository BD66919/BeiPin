package com.example.bj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.example.bj.mapper")
public class BjApplication {

    public static void main(String[] args) {
        SpringApplication.run(BjApplication.class, args);
    }

}

