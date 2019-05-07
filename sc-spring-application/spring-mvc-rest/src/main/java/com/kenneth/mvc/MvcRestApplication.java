package com.kenneth.mvc;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

/**
 * @auther qinkai
 * @data 2019/5/6 18:07
 */
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.kenneth.mvc.controller")
public class MvcRestApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(MvcRestApplication.class)
                .run(args);
    }
}
