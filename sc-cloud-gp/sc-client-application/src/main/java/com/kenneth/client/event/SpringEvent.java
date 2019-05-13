package com.kenneth.client.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @auther qinkai
 * @data 2019/5/11 9:10
 */
public class SpringEvent {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(Object.class);
        //增加监听
        context.addApplicationListener(event ->
                System.err.println("监听到事件 : " + event.getClass().getSimpleName())
        );
        context.refresh();
        context.start();
        context.stop();
        context.close();
    }
}
