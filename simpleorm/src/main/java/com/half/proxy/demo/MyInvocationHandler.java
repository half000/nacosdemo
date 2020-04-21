package com.half.proxy.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: wangwei
 * @Date: 2020-04-13 9:13
 */
public class MyInvocationHandler implements InvocationHandler {

    private Object person;

    public MyInvocationHandler(Object person) {
        this.person = person;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("取信--------------------");
        // 这里会出现一个 InvocationTargetException
        // 这里可以捕获 InvocationTargetException，抛出自己的异常,自己的这个异常类型和接口一致，这样就不会再包装了
        try {
            method.invoke(person, args);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
        System.out.println("送信--------------------");
        return null;
    }
}
