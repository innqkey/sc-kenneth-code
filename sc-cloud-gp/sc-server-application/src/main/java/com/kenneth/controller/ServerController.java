package com.kenneth.controller;

import com.kenneth.annotation.SemaphoreCircuitBreaker;
import com.kenneth.annotation.TimeoutCircuitBreaker;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author qinkai
 * @date 2019年2月20日
 */
@RestController
public class ServerController {

    /**
     * 公共方法
     * @param message
     * @return
     * @throws InterruptedException
     */
    private String doSay2(String message) throws InterruptedException {
        //如果随机时间大于 100，那么触发容断
        int value = random.nextInt(200);
        System.err.println("doSay2() costs " + value + " ms.");
        Thread.sleep(value);
        String returnValue = "Say 2: " + message;
        System.err.println(returnValue);
        return returnValue;
    }

    /*=================================================================================================*/
    //无hystrix时 被客户端调用的方法
    @GetMapping("/say")
    public String say(@RequestParam String message) {
        System.err.println("服务端接收到消息-say: " + message);
        return "Hello, " + message;
    }

    /*=================================================================================================*/
    //hystrix 熔断 简易版
    private final static Random random = new Random();

    @GetMapping("/hystrix/say")
    @HystrixCommand(fallbackMethod = "errorContent",
        commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
                                value = "100")
        })
    public String hystrixSay(@RequestParam String message) throws InterruptedException {
        //如果随机时间大于 100，那么触发容断
        int value = random.nextInt(200);
        System.err.println("hystrixSay() costs " + value + " ms.");
        Thread.sleep(value);
        System.err.println("SeverController 接收到消息-hystrixSay: " + message);
        return "Hello, " + message;
    }
    //注意方法签名要保持一致
    public String errorContent(String message){return "Fault";}

    /*=================================================================================================*/
    //hystrix 熔断 低级版本
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @GetMapping("/say2")
    public String say2(@RequestParam String message) throws Exception{
        Future<String> future = executorService.submit(() -> {
            return doSay2(message);
        });
        String returnValue = null;
        try {
            returnValue = future.get(100, TimeUnit.MILLISECONDS);
        } catch (ExecutionException | InterruptedException |TimeoutException e) {
            // 超级容错 = 执行错误 或 超时
            e.printStackTrace();
            returnValue = errorContent(message);
        }
        return returnValue;
    }

    /*=================================================================================================*/
    //hystrix 熔断 中级版本
    @GetMapping("/middle/say")
    public String middleSay(@RequestParam String message) throws Exception{
        Future<String> future = executorService.submit(() -> {
            return doSay2(message);
        });

        String returnValue = null;
        try {
            returnValue = future.get(100, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            future.cancel(true);  //超时取消任务的执行
            throw e;
        }
        return returnValue;
    }

    /*=================================================================================================*/
    //hystrix 熔断 高级版本 无注解实现
    @GetMapping("/advanced/say")
    public String advancedSay(@RequestParam String message) throws Exception{
        return doSay2(message);
    }

    /*=================================================================================================*/
    //hystrix 熔断 高级版本+注解(超时)
    @GetMapping("/advanced/say2")
    @TimeoutCircuitBreaker(timeout = 100)
    public String advancedSay2(@RequestParam String message) throws Exception{
        return doSay2(message);
    }

    /*=================================================================================================*/
    //hystrix 熔断 高级版本+注解(信号量)
    @GetMapping("/advanced/say3")
    @SemaphoreCircuitBreaker(1)
    public String advancedSay3(@RequestParam String message) throws Exception{
        return doSay2(message);
    }
}
