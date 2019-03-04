package com.kenneth.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kenneth.service.Impl.FeignServiceHystrix;

/**
 * @author qinkai
 * @date 2019年2月20日
 */
//指定要调用的服务名
@FeignClient(value = "eureka-client",fallback = FeignServiceHystrix.class)
public interface FeignService {

    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    String sayHiFromClientOne(@RequestParam(value = "name") String name);

}
