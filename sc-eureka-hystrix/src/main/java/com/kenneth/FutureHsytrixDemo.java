package com.kenneth;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Future 实现熔断 demo
 * @auther qinkai
 * @data 2019/5/4 10:45
 */
public class FutureHsytrixDemo {
    public static void main(String[] args) {
        Random random = new Random();
        ExecutorService service = Executors.newFixedThreadPool(1);
        Future<String> future = service.submit(() -> {
            //如果随机时间大于100，那么触发熔断
            int value = random.nextInt(200);
            System.out.println("Hello world() costs " + value + " ms.");
            Thread.sleep(value);
            return "hello world";
        });

        try {
            future.get(100, TimeUnit.MILLISECONDS);
        } catch (Exception e){
            //超时流程
            System.out.println("超时保护！");
        }
        service.shutdown();
    }
}
