package com.half.aop.springaop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 切面测试 第1个切面
 *
 * @Author: wangwei
 * @Date: 2020-04-25 9:38
 */
@Component
@Aspect
@Order(1)
public class MyAspect1 {

    @Pointcut("execution(* com.half.proxy.demo.Lissa.sendMail(..))")
    public void MyPointcut() {

    }

    @Pointcut("execution(* com.half.proxy.demo.Lissa.sendMail1(..))")
    public void MyPointcut1() {

    }


    @Around("MyPointcut()")
    public Object round(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around before 1");
        Object rtn = joinPoint.proceed();
        System.out.println("around after  1");
        return rtn;
    }

    @Before("MyPointcut()||MyPointcut1()")
    public void before() {
        System.out.println("******* before 1*******");
    }

    @After("MyPointcut()")
    public void after() {
        System.out.println("******* after 1*******");
    }

    @AfterThrowing("MyPointcut()")
    public void afterThrowing() {
        System.out.println("******* afterThrowing 1*******");
    }

    @AfterReturning(value = "MyPointcut()", returning = "msg")
    public void afterReturning(String msg) {
        System.out.println("******* afterReturning 1*******" + msg);
    }


}
