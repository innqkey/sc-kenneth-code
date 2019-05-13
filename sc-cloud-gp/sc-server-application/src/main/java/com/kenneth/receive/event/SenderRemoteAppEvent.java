package com.kenneth.receive.event;

import org.springframework.context.ApplicationEvent;

/**
 * @auther qinkai
 * @data 2019/5/11 13:45
 */
public class SenderRemoteAppEvent extends ApplicationEvent{
    private  String sender;
    public SenderRemoteAppEvent(String sender,Object value) {
        super(value);
        this.sender = sender;
    }

    public String getSender() {
        return sender;
    }

    /*@EventListener
    @Async
    public void onEvent(SenderRemoteAppEvent event) {
        System.err.println("接收到事件源 : " +
                event.getClass().getSimpleName() +
                " , 来自应用 : " + event.getSender() +
                " , 事件内容 ：" + event.getSource());
    }*/
}
