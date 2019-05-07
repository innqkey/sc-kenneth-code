package com.kenneth.controller;

import com.kenneth.service.HystrixService;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;


/**
 * @author qinkai
 * @date 2019年2月20日
 */
@RestController
public class HystrixCommandController {

    private final Random random = new Random();

    @GetMapping("hello-world")
    public String helloWorld(){
        return new HelloWorldCommand().execute();
    }

    /**
     * 算定义实现熔断编程方式
     */
    private class HelloWorldCommand extends HystrixCommand<String>{

        protected HelloWorldCommand(){
            super(HystrixCommandGroupKey.Factory.asKey("World"),100);
        }
        @Override
        protected String run() throws Exception {
            //如果随机时间大于100，那么触发熔断
            int value = random.nextInt(200);
            System.out.println("hello world() costs " + value + " ms..........");
            Thread.sleep(value);
            return "hello";
        }

        protected String getFallback(){
            return HystrixCommandController.this.errContent();
        }
    }

    private String errContent() {
        return "Fault";
    }
}
