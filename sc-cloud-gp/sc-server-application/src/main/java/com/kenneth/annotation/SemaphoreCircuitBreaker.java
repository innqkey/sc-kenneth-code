package com.kenneth.annotation;

import java.lang.annotation.*;

/**
 * 配合controller @GetMapping("/advanced/say3") 方法使用
 * @auther qinkai
 * @data 2019/5/9 14:26
 */
@Target(ElementType.METHOD)          //标注在方法
@Retention(RetentionPolicy.RUNTIME)  //运行时保存注解信息
@Documented
public @interface SemaphoreCircuitBreaker {

    /**
     * 信号量 设置超时时间
     * @return
     */
    int value();
}
