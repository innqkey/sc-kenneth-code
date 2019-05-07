package com.kenneth.reactive.loader;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @auther qinkai
 * @data 2019/5/6 22:24
 */
public class ParallelDataLoader extends DataLoader{
    protected void doLoad() { //并行计算
        long startTime = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(3);  //创建线程池
        CompletionService completionService = new ExecutorCompletionService(executorService);
        completionService.submit(super::loadConfigurations, null);    //耗时 >= 1s
        completionService.submit(super::loadUsers, null);             //耗时 >= 2s
        completionService.submit(super::loadOrders, null);            //耗时 >= 3s
        int count = 0;
        while (count < 3){   //等待3个任务完成
            if (completionService.poll() != null) {
                count++;
            }
        }
        executorService.shutdown();
        long endTime = System.currentTimeMillis();
        System.err.println("执行总耗时：" + (endTime - startTime) + "s");
    } //总耗时 max(1s,2s,3s) >= 3s

    public static void main(String[] args) {
        new ParallelDataLoader().doLoad();
    }
}
