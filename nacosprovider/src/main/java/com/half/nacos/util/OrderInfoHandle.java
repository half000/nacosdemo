package com.half.nacos.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: wangwei
 * @Date: 2019/11/17 21:54
 */
public class OrderInfoHandle<T> implements InvocationHandler {

    private Object targetObject;

    public OrderInfoHandle(T a) {
        targetObject = a;
    }

    /***
     * @Description: 代理对象实际执行方法
     *
     * @Param proxy: 代理对象
     * @Param method: 代理的方法
     * @Param args:  参数
     * @return: java.lang.Object 执行结果
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Object ret = method.invoke(targetObject, args);
        System.out.println("after");
        return ret;
    }
}
