package com.half.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 切面测试 第1个切面
 *
 * @Author: wangwei
 * @Date: 2020-04-25 9:38
 */
@Component
@Aspect
public class MyAspect {

    @Pointcut("execution(* com.half.service.PersonImpl.selectCount(..))")
    public void MyPointcut() {

    }


    @Around("MyPointcut()")
    public Object round(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around before 1");
        Object rtn = joinPoint.proceed();
        System.out.println("around after  1");
        return rtn;
    }

    @Before("MyPointcut()")
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
