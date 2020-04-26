package com.half.aop.springaop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 切面测试 第2个切面
 *
 * @Author: wangwei
 * @Date: 2020-04-25 9:45
 */
@Component
@Aspect
@Order(2)
public class MyAspect2 {

    @Pointcut("execution(* com.half.proxy.demo.Lissa.sendMail(..))")
    public void MyPointcut() {

    }


    @Around("MyPointcut()")
    public Object round(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("round before  2");
        Object rtn = joinPoint.proceed();
        System.out.println("round after  2");
        return rtn;
    }

    @Before("MyPointcut()")
    public void before() {
        System.out.println("******* before 2*******");
    }

    @After("MyPointcut()")
    public void after() {
        System.out.println("******* after 2*******");
    }

    @AfterThrowing("MyPointcut()")
    public void afterThrowing() {
        System.out.println("******* afterThrowing 2*******");
    }

    @AfterReturning(value = "MyPointcut()", returning = "msg")
    public void afterReturning(String msg) {
        System.out.println("******* afterReturning 2*******" + msg);
    }

}
