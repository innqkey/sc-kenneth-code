package com.kenneth.client.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.EventListener;

/**
 * @auther qinkai
 * @data 2019/5/11 9:16
 */
public class SpringAnnotationDrivenEvent {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //SpringAnnotationDrivenEvent 注册 为 Spring Bean
        context.register(SpringAnnotationDrivenEvent.class);
        context.refresh();  // 启动上下文
        //确保上下文启动完毕后，在发送事件
        context.publishEvent(new MyApplicationEvent("Hello World"));
        context.close();
    }

    private static class MyApplicationEvent extends ApplicationEvent {
        public MyApplicationEvent(Object source) {
            super(source);
        }
    }

    @EventListener
    public void onMessage(MyApplicationEvent event) {
        System.err.println("监听到MyApplicationEvent 事件源 : " + event.getSource());
    }
}
