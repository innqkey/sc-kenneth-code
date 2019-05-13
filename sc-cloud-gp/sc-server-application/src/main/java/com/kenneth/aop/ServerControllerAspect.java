package com.kenneth.aop;

import com.kenneth.annotation.SemaphoreCircuitBreaker;
import com.kenneth.annotation.TimeoutCircuitBreaker;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.lang.reflect.Method;
import java.util.concurrent.*;

/**
 * 配合controller层@GetMapping("/advanced/say")方法使用
 * @auther qinkai 切面
 * @data 2019/5/9 12:11
 */
@Aspect
@Component
public class ServerControllerAspect {

    private ExecutorService executorService = Executors.newFixedThreadPool(20);

    private String errorContent(String message) {return "Fault";}

    private volatile Semaphore semaphore = null;

    @PreDestroy
    public void destroy() {executorService.shutdown();}

    private Object doInvoke(ProceedingJoinPoint point, String message, long timeout) throws Throwable{
        Future<Object> future = executorService.submit(() -> {
            Object returnValue = null;
            try {
                returnValue = point.proceed(new Object[]{message});
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            return returnValue;
        });
        Object returnValue = null;
        try {
            returnValue = future.get(timeout, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            future.cancel(true);    //超时取消任务执行
            returnValue = errorContent("");
        }
        return returnValue;
    }


    /**
     * 方法实现版本1
     * @param point
     * @param message
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.kenneth.controller.ServerController.advancedSay(..)) && args(message)")
    public Object advancedSayInTimeout(ProceedingJoinPoint point, String message) throws Throwable {
        return doInvoke(point, message, 100);
    }


    /**
     * 方法实现版本2 注解(超时)
     * @param point
     * @param message
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.kenneth.controller.ServerController.advancedSay2(..)) && args(message)")
    public Object advancedSay2InTimeout(ProceedingJoinPoint point, String message) throws Throwable {
        long timeout = -1;
        if (point instanceof MethodInvocationProceedingJoinPoint) {
            MethodInvocationProceedingJoinPoint methodPoint = (MethodInvocationProceedingJoinPoint) point;
            MethodSignature signature = (MethodSignature)methodPoint.getSignature();
            Method method = signature.getMethod();
            TimeoutCircuitBreaker circuitBreaker = method.getAnnotation(TimeoutCircuitBreaker.class);
            timeout = circuitBreaker.timeout();

        }
        return doInvoke(point, message,timeout);
    }

    /**
     * 方法实现版本3 注解(信号量)  单机版限流方案
     * @param point
     * @param message
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.kenneth.controller.ServerController.advancedSay3(..))" +
            " && args(message)" +
            " && @annotation(circuitBreaker)")
    public Object advancedSay3InSemaphore(ProceedingJoinPoint point, String message,
                                          SemaphoreCircuitBreaker circuitBreaker) throws Throwable {
        int value = circuitBreaker.value();
        if (null == semaphore) {
            semaphore = new Semaphore(value);
        }
        Object returnValue = null;
        try {
            if (semaphore.tryAcquire()) {
                returnValue = point.proceed(new Object[]{message});
                Thread.sleep(1000);
            } else {
                returnValue = errorContent("");
            }
        } finally {
            semaphore.release();
        }
        return returnValue;
    }
}
