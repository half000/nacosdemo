package com.half.proxy.demo;


/**
 * @Author: wangwei
 * @Date: 2020-04-13 10:23
 */
public class Lissa implements Person {
    @Override
    public void sendMail() throws RuntimeException {
        System.out.println("Lissa写了一封信");
        //这里jvm反射调用会封装成 InvocationTargetException
        //throw new RuntimeException("发生异常");
    }

    @Override
    public void sendMail1() throws Exception {
        System.out.println("Lissa写了一封信");
        //这里jvm反射调用会封装成 InvocationTargetException
        // throw new Exception("发生异常");
    }
}
