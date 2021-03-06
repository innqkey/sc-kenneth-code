package com.kenneth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author qinkai
 * @date 2019年2月20日
 */
@RestController
public class EurekaClientController {

    @Value("${server.port}")
    String port;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/server/instances/{serviceName}")
    public List<String> getAllServiceInstances(@PathVariable String serviceName){
        return discoveryClient.getInstances(serviceName)
                .stream()
                .map(s ->
                    s.getServiceId() + "-" + s.getHost() + " :" + s.getPort()
                ).collect(Collectors.toList());
    }
    
    @GetMapping("/hi")
    public String home(@RequestParam(value = "name",defaultValue = "kenneth") String name){
        return "hi " + name + " , i am from port: " + port;
    }
}
