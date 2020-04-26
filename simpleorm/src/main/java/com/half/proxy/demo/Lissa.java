package com.half.proxy.demo;
import org.springframework.stereotype.Service;

/**
 * @Author: wangwei
 * @Date: 2020-04-13 10:23
 */
@Service
public class Lissa implements Person {
    @Override
    public String sendMail() throws RuntimeException {
        System.out.println("Lissa写了一封信");
        return "Lissa写了一封信";
        //这里jvm反射调用会封装成 InvocationTargetException
        //throw new RuntimeException("发生异常");
    }

    @Override
    public String sendMail1() throws Exception {
        System.out.println("Lissa又写了一封信");
        return "Lissa又写了一封信";
        //这里jvm反射调用会封装成 InvocationTargetException
        // throw new Exception("发生异常");
    }
}
