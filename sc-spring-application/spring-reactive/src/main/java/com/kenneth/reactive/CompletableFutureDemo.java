package com.kenneth.reactive;

import java.util.concurrent.CompletableFuture;

/**
 * @auther qinkai
 * @data 2019/5/7 9:04
 */
public class CompletableFutureDemo {
    public static void main(String[] args) {
        println("当前线程");
        CompletableFuture.supplyAsync(() -> {
            println("第一步返回 \"Hello\" ");
            return "Hello";
        }).thenApplyAsync(result -> {  //异步？
            println("第二步在第一步结果 + \" , World\" ");
            return result + ", World";
        }).thenAccept(CompletableFutureDemo::println) //控制输出
            .whenComplete((v,e) -> {
                println("执行结束！");
            }).join(); //等待执行结束
    try{

    } catch(Exception e){

    } finally {

    }
    }
    private static void println(String message) {
        System.err.printf("[线程 : %s] %s \n",
                Thread.currentThread().getName(), //当前线程名字
                message);
    }
}
