package com.kenneth;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * @auther qinkai
 * @data 2019/5/5 17:10
 */
@Configuration
public class SpringAnnotationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(SpringAnnotationDemo.class);
        context.refresh();
        System.out.println(context.getBean(SpringAnnotationDemo.class));
    }
}
