package com.kenneth.controller;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 单机版 事件发布，接收
 * @auther qinkai
 * @data 2019/5/11 9:38
 */
@RestController
public class SpringEventController implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    @GetMapping("/send/event")
    public String sendEvent(@RequestParam String message){
        publisher.publishEvent(message);
        return "Send";
    }

    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    @EventListener
    public void onMessage(PayloadApplicationEvent publisher){
        System.err.println("接收到事件 : " + publisher.getPayload());
    }
}
