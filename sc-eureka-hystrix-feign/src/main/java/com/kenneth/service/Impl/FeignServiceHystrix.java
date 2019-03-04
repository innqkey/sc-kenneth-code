package com.kenneth.service.Impl;

import org.springframework.stereotype.Service;

import com.kenneth.service.FeignService;

/**
 * @author qinkai
 * @date 2019年3月4日
 */
@Service
public class FeignServiceHystrix implements FeignService{

    public String sayHiFromClientOne(String name) {
        return "sorry , " + name;
    }

}
