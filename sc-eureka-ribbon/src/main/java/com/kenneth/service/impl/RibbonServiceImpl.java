package com.kenneth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kenneth.service.RibbonService;

/**
 * @author qinkai
 * @date 2019年2月20日
 */
@Service
public class RibbonServiceImpl implements RibbonService{

    @Autowired
    RestTemplate restTemplate;
    
    @Override
    public String hiService(String name) {
        return restTemplate.getForObject("http://eureka-client/hi?name="+name, String.class);
    }

}
