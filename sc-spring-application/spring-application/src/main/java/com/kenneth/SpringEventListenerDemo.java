package com.kenneth;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @auther qinkai
 * @data 2019/5/5 17:23
 */
public class SpringEventListenerDemo {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
            @Override
            public void onApplicationEvent(ApplicationEvent event) {
                System.out.println("监听事件： " + event);
            }
        });
        context.addApplicationListener(new ClosedListener());
        context.addApplicationListener(new RefreshedListener());
        context.refresh();
        context.publishEvent("Hello world");
        context.publishEvent(new MyEvent("my event........."));
        context.close();
    }

    private static class RefreshedListener implements ApplicationListener<ContextRefreshedEvent> {
        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            System.out.println("上下文启动："  + event.getClass().getSimpleName());
        }
    }

    private static class ClosedListener implements ApplicationListener<ContextClosedEvent> {
        @Override
        public void onApplicationEvent(ContextClosedEvent event) {
            System.out.println("上下文关闭："  + event.getClass().getSimpleName());
        }
    }

    private static class MyEvent extends ApplicationEvent {
        public MyEvent(Object source) {
            super(source);
        }
    }
}
