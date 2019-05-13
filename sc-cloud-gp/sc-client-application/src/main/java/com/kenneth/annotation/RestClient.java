package com.kenneth.annotation;

import java.lang.annotation.*;

/**
 * 自定义实现 Feign
 * Rest Client 注解
 * @auther qinkai
 * @data 2019/5/9 18:35
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RestClient {

    /**
     * REST 服务应用名称
     */
    String name();
}
