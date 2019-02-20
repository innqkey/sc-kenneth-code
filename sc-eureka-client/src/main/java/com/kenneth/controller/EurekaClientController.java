package com.kenneth.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qinkai
 * @date 2019年2月20日
 */
@RestController
public class EurekaClientController {

    @Value("${server.port}")
    String port;
    
    @GetMapping("/hi")
    public String home(@RequestParam(value = "name",defaultValue = "kenneth") String name){
        return "hi " + name + " , i am from port: " + port;
    }
}
