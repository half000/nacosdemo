package com.half.proxy.demo;

/**
 * @Author: wangwei
 * @Date: 2020-04-13 10:20
 */
public interface Person {

    void sendMail() throws RuntimeException; //非检查异常

    void sendMail1() throws Exception; //检查异常

}
