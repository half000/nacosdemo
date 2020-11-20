package com.half.nacos.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: wangwei
 * @Date: 2020-11-17 15:57
 */
@Configuration
public class IceAutoConfiguration {

    @Autowired
    SpringClientFactory springClientFactory;

    @Bean
    public IceLoadBalancer iceLoadBalancerClient() {
        return new IceLoadBalancerClient(springClientFactory);
    }

}
