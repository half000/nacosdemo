package com.half.nacos.config;

import org.springframework.context.annotation.Configuration;

/**
 * @Author: wangwei
 * @Date: 2019/11/10 19:01
 */
@Configuration
public class BeforeTestA {

    static{
       // System.out.println("BeforeTestA.class loaded");
    }

    public BeforeTestA(){
        //System.out.println("BeforeTestA instanted");
    }
}
