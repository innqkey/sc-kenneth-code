package com.kenneth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @auther qinkai
 * @data 2019/5/5 15:34
 */
@SpringBootApplication
public class ProjectApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ProjectApplication.class);
        Map<String, Object> properties = new LinkedHashMap<>();
        properties.put("server.port", 0);
        springApplication.setDefaultProperties(properties);
        //设置非web工程
        springApplication.setWebApplicationType(WebApplicationType.NONE);
        ConfigurableApplicationContext applicationContext = springApplication.run(args);
        System.out.println(applicationContext.getBean(ProjectApplication.class));
        System.out.println("当前Spring 应用上下文：" + applicationContext.getClass().getSimpleName());
    }

}
