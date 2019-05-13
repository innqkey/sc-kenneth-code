package com.kenneth.controller;

import com.kenneth.service.feign.SayingRestService;
import com.kenneth.service.feign.SayingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务调用 Feign
 * @auther qinkai
 * @data 2019/5/9 16:09
 */
@RestController
public class FeignClientController {

    /*=====================================================================================*/
    //原生 Feign
    @Autowired
    private SayingService sayingService;

    @GetMapping("/feign/say")
    public String feignSay(@RequestParam String message) {
        return sayingService.say(message);
    }

    /*=====================================================================================*/
    //自定义实现Feign

    @Autowired
    private SayingRestService sayingRestService;

    @GetMapping("/rest/say")
    public String restSay(@RequestParam String message) {
        return sayingRestService.say(message);
    }
}
