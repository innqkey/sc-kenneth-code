package com.kenneth.mvc.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.*;

/**
 * @auther qinkai
 * @data 2019/5/6 17:55
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequestMapping(method = RequestMethod.OPTIONS)  //如果不增加元注解的话，会报错
public @interface OptionsMapping {
    //需要重新定义属性
    @AliasFor(annotation = RequestMapping.class)  //指定requestmapping 属性
    String name() default "";                     //不加的话，只能代表自己
}
