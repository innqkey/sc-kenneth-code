package com.kenneth.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 自定义 rest client
 * @auther qinkai
 * @data 2019/5/9 18:47
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(RestClientsRegistrar.class)
public @interface EnableRestClient {

    /**
     * 指定 @RestClient 接口
     */
    Class<?>[] clients() default {};
}
