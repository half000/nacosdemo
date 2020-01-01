package com.half.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: wangwei
 * @Date: 2019/10/26 8:46
 */
@SpringBootApplication(exclude = com.half.base.ProviderFeignConfiguration.class)
@EnableDiscoveryClient( autoRegister  = true)
public class NacosProvider {

    public static void main(String[] args) {

        /*System.out.println("sys***********");
        System.getProperties().forEach((key,value)->System.out.println(key+":  "+value));
        System.out.println("env***********");
        System.getenv().forEach((key,value)->System.out.println(key+":  "+value));*/
       // System.out.println(System.getProperty("java.class.path"));
        SpringApplication.run(NacosProvider.class,args);

    }


}
