package com.kenneth;

import com.kenneth.annotation.EnableRestClient;
import com.kenneth.client.event.HttpRemoteAppEventListener;
import com.kenneth.service.feign.SayingRestService;
import com.kenneth.service.feign.SayingService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author qinkai
 * @date 2019年1月25日
 */
@SpringBootApplication    //标准Springboot应用
@EnableDiscoveryClient    //激活服务发现客户端
@EnableScheduling         //激活任务调度
@EnableFeignClients(clients = SayingService.class)    //引入 FeignClient
@EnableRestClient(clients = SayingRestService.class)    //引入 自定义Rest Client
public class ClientApplication {
    
    public static void main(String[] args) {
        new SpringApplicationBuilder(ClientApplication.class)
                .web(WebApplicationType.SERVLET)
                .listeners(new HttpRemoteAppEventListener())
                .run(args);
    }
}
