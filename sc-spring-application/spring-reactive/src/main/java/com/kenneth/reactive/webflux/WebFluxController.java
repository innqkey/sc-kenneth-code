package com.kenneth.reactive.webflux;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @auther qinkai
 * @data 2019/5/6 21:16
 */
@RestController
public class WebFluxController {

    @RequestMapping("")
    public Mono<String> index() {
        //执行计算
        println("执行计算");
        Mono<String>result = Mono.fromSupplier(() -> {
            println("返回结果");
            return "Hello World";
        });
        return result;
    }

    private static void println(String message) {
        System.err.printf("[线程 : %s] %s \n",
                Thread.currentThread().getName(), //当前线程名字
                message);
    }
}
