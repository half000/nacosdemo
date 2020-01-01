package com.half.nacos.config;

import org.springframework.stereotype.Component;

/**
 * @Author: wangwei
 * @Date: 2019/11/13 21:44
 */
@Component
public class TestBeanB {

    static {
       // System.out.println("TestBeanB class load");
    }

    {
        System.out.println("TestBeanB instant");
    }
}
