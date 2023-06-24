package com.example.springcheck;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Slf4j  // 加上注解可以直接在类中使用log打印日志
@SpringBootApplication  //springboot启动类
public class MainApplication {
    public static void main(String[] args) {

        SpringApplication.run(MainApplication.class, args);
    }
}