package com.kenneth.controller;

import com.kenneth.client.event.RemoteAppEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 远程应用事件控制器
 * @auther qinkai
 * @data 2019/5/11 10:39
 */
@RestController
public class RemoteAppEventSenderController implements ApplicationEventPublisherAware {

    @Value("${spring.application.name}")
    private String currentAppName;

    private ApplicationEventPublisher publisher;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    /*=========================================================================*/
    /**
     * 集群发送，该应用下所有实例都会接收到
     * @param appName
     * @param message
     * @return
     */
    @GetMapping("/send/remote/event/{appName}")
    public String sendAppCluster(@PathVariable String appName,
                                 @RequestParam String message) {
        //List<ServiceInstance> serviceInstances = discoveryClient.getInstances(appName);
        RemoteAppEvent remoteAppEvent = new RemoteAppEvent(message,appName,true);
        //发送事件到当前上下文
        publisher.publishEvent(remoteAppEvent);
        return "OK";
    }

    /*=========================================================================*/
    /**  FIXME  待完善
     * 给指定实例发送数据
     * @param appName
     * @param ip
     * @param port
     * @param data
     * @return
    @PostMapping("/send/remote/event/{appName}/{ip}/{port}")
    public String sendAppInstance(@PathVariable String appName,
                                  @PathVariable String ip,
                                  @PathVariable int port,
                                  @RequestBody Object data) {
        ServiceInstance serviceInstance = new DefaultServiceInstance(appName,ip,port,false);
        RemoteAppEvent remoteAppEvent = new RemoteAppEvent(data,appName,serviceInstance);
        //发送事件到当前上下文
        publisher.publishEvent(remoteAppEvent);
        return "OK";
    }*/
}
