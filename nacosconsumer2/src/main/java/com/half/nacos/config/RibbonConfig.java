package com.half.nacos.config;

import feign.Request;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;

/**
 * @Author: wangwei
 * @Date: 2019-12-24 22:09
 */
@RibbonClient(name="nacosprovider",configuration = {RibbonConfig.class})
public class RibbonConfig {

    @Bean
    Request.Options getOption(){
        return new Request.Options(7000,8000);
    }

}
