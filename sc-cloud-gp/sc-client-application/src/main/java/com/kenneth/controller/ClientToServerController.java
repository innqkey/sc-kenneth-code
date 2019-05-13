package com.kenneth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 自定义负载均衡：版本2 当前客户端调用服务端api返回
 * @author qinkai
 * @date 2019年2月20日
 */
@RestController
public class ClientToServerController {

/*    @Autowired
    private DiscoveryClient discoveryClient;
    //依赖注入自定义bean
    @Autowired
    private RestTemplate restTemplate;
    @Bean    // 自定义 RestTemplate Bean 和别外的controller中 restTemplate冲突，测试时，只能使用一个，屏蔽别一个
    public RestTemplate restTemplate(){ return new RestTemplate();}

    //线程安全
    private volatile Map<String, Set<String>> targetUrlsCache = new HashMap<>();

    @Scheduled(fixedRate = 10 * 1000) //10s更新一次缓存
    public void updateTargetUrlsCache() { //更新目标
        //获取当前应用的机器列表
        //http://${ip}:${port}
        Map<String, Set<String>> newTargetUrlsCache = new HashMap<>();
        discoveryClient.getServices().forEach(serviceName ->{
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);
        Set<String> newTargetUrls = serviceInstances
                .stream()
                .map(s -> s.isSecure() ?
                        "https://" + s.getHost() + ":" + s.getPort():
                        "http://" + s.getHost() + ":" + s.getPort()
                ).collect(Collectors.toSet());
            newTargetUrlsCache.put(serviceName, newTargetUrls);
        } );
        this.targetUrlsCache = newTargetUrlsCache;
    }

    @GetMapping("/invoke/{serviceName}/say")
    public String invokeSay(@PathVariable String serviceName,
            @RequestParam String message){
        //服务器列表快照
        List<String> targetUrls = new LinkedList<>(targetUrlsCache.get(serviceName));
        //轮训列表
        int size = targetUrls.size();
        //size = 3, index = 0~2
        int index = new Random().nextInt(size);
        //选择其中一台服务器
        String targetURL = targetUrls.get(index);
        //RestTemplate 发送请求到服务器
        //输出响应
        return restTemplate.getForObject(targetURL + "/say?message=" + message, String.class);
    }*/

}
