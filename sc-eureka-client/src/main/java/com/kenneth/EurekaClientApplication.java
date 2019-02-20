package com.kenneth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author qinkai
 * @date 2019年1月25日
 */
@SpringBootApplication
@EnableEurekaClient
public class EurekaClientApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }
}
