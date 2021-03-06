package com.kenneth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther qinkai
 * @data 2019/5/7 15:40
 */
@SpringBootApplication
@EnableConfigServer
public class SpringCloudConfigServer {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConfigServer.class,args);
    }

    //可以在此处替换掉默认为git配置属性
    @Bean
    public EnvironmentRepository environmentRepository() {
        return (String application,String profile,String label) ->{
            Environment environment = new Environment("default", profile);
            List<PropertySource>propertySources = environment.getPropertySources();
            Map<String,Object> source = new HashMap<>();
            source.put("name","kenneth");
            PropertySource propertySource = new PropertySource("map",source);
            propertySources.add(propertySource);
            return environment;
        };
    }
}
