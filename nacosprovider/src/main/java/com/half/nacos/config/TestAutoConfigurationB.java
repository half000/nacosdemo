package com.half.nacos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: wangwei
 * @Date: 2019/11/10 10:31
 */
@Configuration
public class TestAutoConfigurationB {

    public TestAutoConfigurationB() {
        //System.out.println("TestAutoConfigurationB instanted");
    }

    static{
        //System.out.println("TestAutoConfigurationB class loaded");
    }


    @Bean("getTestBeanA1")
    public TestBeanA getTestBeanA(){
        return new TestBeanA();
    }

    @Bean("getTestBeanB1")
    public TestBeanB getTestBeanB(){
        System.out.println("TestAutoConfigurationB.getTestBeanB");
        return new TestBeanB();
    }
}
