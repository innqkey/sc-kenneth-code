package com.kenneth;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @auther qinkai
 * @data 2019/5/5 13:11
 */
@EnableAutoConfiguration
public class SpringBootEventDemo {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringBootEventDemo.class)
                .listeners(event ->{
                    //增加监听器
                    System.err.println("监听到事件： " + event.getClass().getSimpleName());
                })
                .run(args)
                .close();
    }
}
