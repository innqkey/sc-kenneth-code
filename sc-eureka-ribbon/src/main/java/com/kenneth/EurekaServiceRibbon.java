package com.kenneth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author qinkai
 * @date 2019年1月25日
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class EurekaServiceRibbon {
    
    public static void main(String[] args) {
        SpringApplication.run(EurekaServiceRibbon.class, args);
    }
    
    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
