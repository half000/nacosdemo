package com.half.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * @Author: wangwei
 * @Date: 2019/11/18 21:58
 */
@SpringCloudApplication
@EnableHystrix
@EnableDiscoveryClient
public class NacosConsumer {

    public static void main(String[] args) {
        SpringApplication.run(NacosConsumer.class,args );
    }
}
