package com.half.aop.demo;

import com.half.aop.annotation.MyAround;
import com.half.aop.annotation.MyAspect;
import com.half.aop.annotation.MyBefore;
import com.half.aop.annotation.MyPointcut;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @Author: wangwei
 * @Date: 2020-04-22 17:22
 */
@MyAspect
public class MyAspectDemo {

    @MyPointcut("execution(* com.half.proxy.demo.Lissa.sendMail(..))")
    public void point() {
    }

    @MyAround("point")
    public void round(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("MyAround before");
        joinPoint.proceed();
        System.out.println("MyAround after");
    }

    @MyBefore("point")
    public void before() {
        System.out.println("******* before *******");
    }

}
