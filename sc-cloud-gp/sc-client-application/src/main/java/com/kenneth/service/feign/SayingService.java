package com.kenneth.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @auther qinkai
 * @data 2019/5/9 16:02
 */
@FeignClient(name = "server-application")  //指定要调用的服务名称
public interface SayingService {

    @GetMapping("/say")
    String say(@RequestParam("message") String message);
}
