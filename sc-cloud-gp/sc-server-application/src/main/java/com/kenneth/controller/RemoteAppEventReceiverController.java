package com.kenneth.controller;

import com.kenneth.receive.event.SenderRemoteAppEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 远程事件接收器 控制器
 *
 * @auther qinkai
 * @data 2019/5/11 13:33
 */
@RestController
public class RemoteAppEventReceiverController implements ApplicationEventPublisherAware {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @PostMapping("/receive/remote/event")
    public String receive(@RequestBody Map<String, Object> data) { //rest 请求不需要具体类型
        //事件的发送者
        String sender = (String) data.get("sender");
        //事件的数据内容
        Object source = data.get("source");
        //事件类型
        String type = (String) data.get("type");
        //接受到的对象内容，同样也要发送事件到本地，做处理
        publisher.publishEvent(new SenderRemoteAppEvent(sender, source));
        return "received";
    }

    @EventListener
    @Async
    public void onEvent(SenderRemoteAppEvent event) {
        System.err.println("接收到事件源 : " +
                event.getClass().getSimpleName() +
                " , 来自应用 : " + event.getSender() +
                " , 事件内容 ：" + event.getSource());
    }

}
