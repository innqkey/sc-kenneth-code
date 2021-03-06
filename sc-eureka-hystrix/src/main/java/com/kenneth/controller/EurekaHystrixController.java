package com.kenneth.controller;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kenneth.service.HystrixService;

import java.util.Random;


/**
 * @author qinkai
 * @date 2019年2月20日
 */
@RestController
public class EurekaHystrixController {

    @Autowired
    HystrixService hystrixService;
    
    @GetMapping("/hi")
    public String home(@RequestParam String name){
        return hystrixService.hiService(name);
    }

}
