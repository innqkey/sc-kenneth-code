package com.kenneth;

import rx.Observer;
import rx.Single;
import rx.schedulers.Schedulers;

import java.util.Random;

/**
 * ReactiveX Java Demo
 * @auther qinkai
 * @data 2019/5/4 10:56
 */
public class RxJavaDemo {
    public static void main(String[] args) {
        Random random = new Random();
        Single.just("Hello,World") //just 发布数据
                .subscribeOn(Schedulers.immediate()) //订阅的线程池 immediate = Thread.currentThread()
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("执行结束........");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("熔断保护.........");
                    }

                    @Override
                    public void onNext(String s) { //数据消费 s = hello world
                        //如果随机数大于100，那么触发熔断
                        int value = random.nextInt(200);

                        if(value > 100){
                            throw new RuntimeException("Time out .........");
                        }
                        System.out.println("Hello world() costs " + value + " ms.....");
                    }
                });
    }
}
