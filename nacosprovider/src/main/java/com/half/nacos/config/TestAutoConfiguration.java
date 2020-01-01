package com.half.nacos.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: wangwei
 * @Date: 2019/11/10 10:31
 */
@Configuration
@AutoConfigureAfter({ BeforeTestA.class,
        TestAutoConfigurationB.class
        })
public class TestAutoConfiguration {

    public TestAutoConfiguration() {
       // System.out.println("TestAutoConfiguration instanted");
    }

    @Bean
    public TestBeanA getTestBeanA(){
        return new TestBeanA();
    }

    @Bean
    public TestBeanB getTestBeanB(){
        System.out.println("TestAutoConfiguration.getTestBeanB");
        return new TestBeanB();
    }

    static{
       // System.out.println("TestAutoConfiguration class loaded");
    }
}
