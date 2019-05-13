package com.kenneth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 自定义负载均衡：版本1 当前客户端调用自己的api返回
 * @author qinkai
 * @date 2019年2月20日
 */
@RestController
public class ClientToClientController {
/*
    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${spring.application.name}")
    private String currentServiceName;

    //依赖注入自定义bean
    @Autowired
    private RestTemplate restTemplate;
    @Bean    // 自定义 RestTemplate Bean  和别外的controller中 restTemplate冲突，测试时，只能使用一个，屏蔽别一个
    public RestTemplate restTemplate(){ return new RestTemplate();}

    //线程安全
    private volatile Set<String> targetUrls = new HashSet<>();

    @Scheduled(fixedRate = 10 * 1000) //10s更新一次缓存
    public void updateTargetUrlsCache() { //更新目标
        //获取当前应用的机器列表
        //http://${ip}:${port}
        Set<String>oldTargetUrls = this.targetUrls;
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(currentServiceName);
        Set<String> newTargetUrls = serviceInstances
                .stream()
                .map(s -> s.isSecure() ?
                        "https://" + s.getHost() + ":" + s.getPort():
                        "http://" + s.getHost() + ":" + s.getPort()
                ).collect(Collectors.toSet());

        //swap 交换列表
        this.targetUrls = newTargetUrls;
        oldTargetUrls.clear();
    }

    @GetMapping("/invoke/say")
    public String invokeSay(@RequestParam String message){
        //服务器列表
        List<String> targetUrls = new ArrayList<>(this.targetUrls);
        //轮训列表
        int size = targetUrls.size();
        //size = 3, index = 0~2
        int index = new Random().nextInt(size);
        //选择其中一台服务器
        String targetURL = targetUrls.get(index);
        //RestTemplate 发送请求到服务器
        //输出响应
        //快照
        return restTemplate.getForObject(targetURL + "/say?message=" + message, String.class);
    }

    @GetMapping("/say")
    public String sya(@RequestParam String message) {
        System.err.println("接收到消息-say: " + message);
        return "Hello, " + message;
    }*/
}
