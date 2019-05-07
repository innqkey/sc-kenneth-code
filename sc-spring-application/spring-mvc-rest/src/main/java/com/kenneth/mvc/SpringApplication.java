package com.kenneth.mvc;

import com.kenneth.mvc.service.EchoService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.DefaultTransactionStatus;

/**
 * @auther qinkai
 * @data 2019/5/6 17:07
 */
@ComponentScan(basePackages = "com.kenneth.mvc.service")
@EnableTransactionManagement
public class SpringApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册 SpringApplication 扫描 com.kenneth.mvc.service
        context.register(SpringApplication.class);
        context.refresh();//启动
        context.getBeansOfType(EchoService.class).forEach((beanName,bean) -> {
            System.err.println("Bean Name : " + beanName + " ,bean : " + bean);
            bean.echo("Hello, World");
        });
        context.close();  //关闭
    }
    @Component("myTxName")
    public static class MyPlatformTransactionManager implements PlatformTransactionManager{

        @Override
        public TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException {
            return new DefaultTransactionStatus(
                    null,true,true,definition.isReadOnly(),true,null
            );
        }

        @Override
        public void commit(TransactionStatus status) throws TransactionException {
            System.err.println("Commit()...........");
        }

        @Override
        public void rollback(TransactionStatus status) throws TransactionException {
            System.err.println("rollback()..........");
        }
    }
}
