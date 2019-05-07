package com.kenneth.reactive.loader;

import java.util.concurrent.TimeUnit;

/**
 * @auther qinkai
 * @data 2019/5/6 22:00
 */
public class DataLoader {
    public final void load(){
        long startTime = System.currentTimeMillis(); //开始时间
        doLoad();   //具体执行
        long costTime = System.currentTimeMillis() - startTime;
        System.err.println("load() 总耗时： " + costTime);
    }

    protected void doLoad() { //串行计算
        loadConfigurations(); //耗时1s
        loadUsers();          //耗时2s
        loadOrders();         //耗时3s
    }

    protected void loadOrders() {
        loadMock("loadConfiguration()",1);
    }

    protected void loadUsers() {
        loadMock("loadUsers()",2);
    }

    protected void loadConfigurations() {
        loadMock("loadOrders()",3);
    }

    protected void loadMock(String source, int seconds) {
        try {

            long startTime = System.currentTimeMillis();
            long milliseconds = TimeUnit.SECONDS.toMillis(seconds);
            Thread.sleep(milliseconds);
            long costTime = System.currentTimeMillis() - startTime;
            System.err.printf("[线程 ：%s] %s 耗时 ：%d 毫秒\n",
                    Thread.currentThread().getName(),
                    source,
                    costTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new DataLoader().load();
    }
}
