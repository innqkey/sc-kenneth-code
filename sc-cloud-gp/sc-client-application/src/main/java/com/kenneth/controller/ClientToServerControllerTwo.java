package com.kenneth.controller;

import com.kenneth.annotation.CustomizedLoadBalanced;
import com.kenneth.loadbalance.LoadBalancedRequestInterceptor;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 抽离负载均衡算法：版本3 当前客户端调用服务端api返回
 * @author qinkai
 * @date 2019年2月20日
 */
@RestController
public class ClientToServerControllerTwo {

    @Autowired //依赖注入自定义 RestTemplate
    private RestTemplate restTemplate;

    @Bean
    public ClientHttpRequestInterceptor interceptor(){ //交由spring管理
        return new LoadBalancedRequestInterceptor();
    }

    @Bean    // 自定义 RestTemplate Bean
    public RestTemplate restTemplate(ClientHttpRequestInterceptor interceptor){ //依赖注入
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Arrays.asList(interceptor));
        return restTemplate;
    }
    @GetMapping("/invoke/{serviceName}/say")
    public String invokeSay(@PathVariable String serviceName,
            @RequestParam String message){
        //自定义 RestTemplate 发送请求到服务器
        //输出响应
        return restTemplate.getForObject("/" + serviceName + "/say?message=" + message, String.class);
    }

    /*=============================================================================================*/
    //使用Ribbon 的 loadbalance
    @Autowired
    @LoadBalanced   //依赖注入Ribbon RestTemplate
    private RestTemplate lbrestTemplate;

    @Bean           //Ribbon RestTemplate Bean
    @LoadBalanced
    public RestTemplate loadBalancedRestTemplate(){ //依赖注入
        return new RestTemplate();
    }

    @GetMapping("/lb/invoke/{serviceName}/say")
    public String lbinvokeSay(@PathVariable String serviceName,
                            @RequestParam String message){
        //Ribbon RestTemplate 发送请求到服务器
        //输出响应
        return lbrestTemplate.getForObject("http://" + serviceName + "/say?message=" + message, String.class);
    }
    /*=======================================================================================================*/
    /*//自定义loadbalanced --> CustomizedLoadBalanced

    @Bean
    @Autowired
    @CustomizedLoadBalanced
    public RestTemplate restTemplate(){

         return new RestTemplate();
    }

    @Bean
    @Autowired
    public Object customized(@CustomizedLoadBalanced Collection<RestTemplate> restTemplates,
                             ClientHttpRequestInterceptor interceptor) {
        restTemplates.forEach(r->{
            r.setInterceptors(Arrays.asList(interceptor));
        });
        return new Object();
    }*/

}
