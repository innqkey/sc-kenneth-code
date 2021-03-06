package com.kenneth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author qinkai
 * @date 2019年1月25日
 */
@SpringBootApplication     //标准SpringBoot应用
@EnableDiscoveryClient     //激活服务发现
@EnableHystrix             //激活hystrix应用
@EnableAspectJAutoProxy(proxyTargetClass = true) //激活AOP
@EnableAsync
public class ServerApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }
}
