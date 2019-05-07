package com.kenneth;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;

/**
 * @auther qinkai
 * @data 2019/5/5 16:41
 */
public class ApplicationEventMulticasterDemo {
    public static void main(String[] args) {
        ApplicationEventMulticaster multicasterDemo = new SimpleApplicationEventMulticaster();
        //添加监听器
        multicasterDemo.addApplicationListener(event -> {
            if(event instanceof PayloadApplicationEvent){
                System.out.println("接收到 PayloadApplicationEvent: " +
                        PayloadApplicationEvent.class.cast(event).getPayload());
            } else {
                System.out.println("接收到事件：" + event);
            }
        });

        multicasterDemo.multicastEvent(new MyEvent("Hello world"));
        multicasterDemo.multicastEvent(new PayloadApplicationEvent<Object>("2", "Hello-world-2"));
    }


    private static class MyEvent extends ApplicationEvent {
        public MyEvent(Object source) {
            super(source);
        }
    }
}
