package com.kenneth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kenneth.service.FeignService;


/**
 * @author qinkai
 * @date 2019年2月20日
 */
@RestController
public class EurekaFeignController {

    @Autowired
    FeignService feignService;
    
    @GetMapping("/feign")
    public String sayHi(@RequestParam String name){
        return feignService.sayHiFromClientOne(name);
    }
}
