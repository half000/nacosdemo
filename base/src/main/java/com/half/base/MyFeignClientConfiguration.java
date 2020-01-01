package com.half.base;

import com.half.base.hystrix.UserFallBack;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixThreadPoolKey;
import feign.Contract;
import feign.Feign;
import feign.Request;
import feign.hystrix.SetterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: wangwei
 * @Date: 2019-11-25 22:24
 */
@Configuration
public class MyFeignClientConfiguration {
    /*
    feign url映射策略
     */
    //@Bean
    public Contract contract(){
        return new Contract.Default();
    }

    /***
     *  自定义setter工厂，这里可以改变hystrix的配置
     *
     * @return: feign.hystrix.SetterFactory
     */
    @Bean
    public SetterFactory getSetterFactory(){
        return (target,method)->{
            String groupKey = target.name();
            String commandKey = Feign.configKey(target.type(), method);
            return HystrixCommand.Setter
                    .withGroupKey(HystrixCommandGroupKey.Factory.asKey(groupKey))
                    .andCommandKey(HystrixCommandKey.Factory.asKey(commandKey))
                    .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("feign"));
        };

    }

    @Bean
    public UserFallBack userFallBack(){
        return new UserFallBack();

    }

    /**
     * 设置超时策略，这里设置了，则会创建不自动刷新的IClientConfig
     * @return
     */
    @Bean
    public Request.Options feignRequestOptions() {
        return new Request.Options(12*1000,21*1000);
    }
}
