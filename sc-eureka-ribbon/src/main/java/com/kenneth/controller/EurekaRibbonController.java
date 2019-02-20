package com.kenneth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kenneth.service.RibbonService;

/**
 * @author qinkai
 * @date 2019年2月20日
 */
@RestController
public class EurekaRibbonController {

    @Autowired
    RibbonService ribbonService;
    
    @GetMapping("/hi")
    public String home(@RequestParam String name){
        return ribbonService.hiService(name);
    }
}
