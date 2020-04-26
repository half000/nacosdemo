package com.half.proxy.demo;

import com.half.proxy.cglib.MyCglibProxy;
import com.half.proxy.jdk.MyClassLoader;
import com.half.proxy.jdk.MyProxy;
import org.springframework.cglib.core.DebuggingClassWriter;

import java.lang.reflect.Proxy;

/**
 * 动态代理测试类
 *
 * @Author: wangwei
 * @Date: 2020-04-13 9:14
 */
public class TestMain {

    public static void main(String[] args) {
        //testMyJdk();
        testCglib();
    }

    public static void testCglib() {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "C:\\class");
        Person person = (Person) new MyCglibProxy().getProxy(Lissa.class);
        person.sendMail();
        try {
            person.sendMail1();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testJdk() {
        System.setProperty("jdk.proxy.debug", "debug");
        System.setProperty("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");

        Person person = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), Lissa.class.getInterfaces(), new MyInvocationHandler(new Lissa()));

        try {
            person.sendMail();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            person.sendMail1();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void testMyJdk() {
        Person proxy = (Person) MyProxy.newProxyInstance(new MyClassLoader(), Lissa.class.getInterfaces(), new MyInvocationHandler(new Lissa()));
        try {
            proxy.sendMail();
            proxy.sendMail1();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //系统变量与环境变量
    public static void testProp() {
        //系统变量
        System.getProperties().forEach((key, value) -> System.out.println(key + ":" + value));
        //环境变量
        System.getenv().forEach((key, value) -> System.out.println(key + ":" + value));
    }


}
