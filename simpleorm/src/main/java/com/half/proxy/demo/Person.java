package com.half.proxy.demo;

/**
 * @Author: wangwei
 * @Date: 2020-04-13 10:20
 */
public interface Person {

    String sendMail() throws RuntimeException; //非检查异常

    String sendMail1() throws Exception; //检查异常

}
