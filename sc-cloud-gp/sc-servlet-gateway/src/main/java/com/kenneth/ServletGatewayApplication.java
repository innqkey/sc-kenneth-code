package com.kenneth;

import com.kenneth.gateway.loadbalance.ZookeeperLoadBalancer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author qinkai
 * @date 2019年1月25日
 */
@SpringBootApplication     //标准SpringBoot应用
@EnableDiscoveryClient     //激活服务发现
@ServletComponentScan(basePackages = "com.kenneth.gateway.servlet") //扫描指定的包,默认不扫描
@EnableScheduling          //激活调度任务
public class ServletGatewayApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ServletGatewayApplication.class, args);

        Map<String, Integer> map = new ConcurrentHashMap<>();
        map.put("aa",10);
        map.put("aa",20);
        map.put("aa",30);
        System.out.println(map.get("aa"));
    }

    @Bean
    public ZookeeperLoadBalancer zookeeperLoadBalancer(DiscoveryClient discoveryClient) {
        return new ZookeeperLoadBalancer(discoveryClient);
    }
}
