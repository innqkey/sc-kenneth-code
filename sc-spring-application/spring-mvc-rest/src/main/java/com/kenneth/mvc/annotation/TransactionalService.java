package com.kenneth.mvc.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

/**
 * @auther qinkai
 * @data 2019/5/6 16:45
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Service         //它是@Service组件
@Transactional   //它是事务注解
public @interface TransactionalService { //@Service + @Transactional
    @AliasFor(annotation = Service.class)
    String value();  //服务名称

    @AliasFor(annotation = Transactional.class,attribute = "value")
    String txName();
}
