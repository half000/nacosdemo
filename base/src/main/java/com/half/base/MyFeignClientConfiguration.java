package com.half.base;

import com.half.base.hystrix.UserFallBack;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixThreadPoolKey;
import feign.Contract;
import feign.Feign;
import feign.ReflectiveFeign;
import feign.Request;
import feign.hystrix.SetterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * feign 配置
 * feign主要是把方法与url映射关联起来，通过对象方法调用来发出http请求
 * <p>
 * 关联方式是通过注解方式，现支持2种注解合约，一种是mvc的注解，一种是feign自带的注解
 * <p>
 * Feign客户端来实现对象方法调用的方式发出http请求，是采用动态代理来实现的，具体是 feign.Feign#newInstance(feign.Target)方法返回的是代理对象
 *
 * @Author: wangwei
 * @Date: 2019-11-25 22:24
 * @see ReflectiveFeign  通过基本实现类的newInstance创建代理对象，通过
 * @see feign.InvocationHandlerFactory  来控制调用句柄的实例化,这里支持各种扩展
 * @see ReflectiveFeign.ParseHandlersByName 创建方法handle映射（这里根据合约类型创建）
 */
@Configuration
public class MyFeignClientConfiguration {
    /**
     * 支持2种注解合约，一种是mvc的注解，一种是feign自带的注解
     * feign url映射策略
     */
    //@Bean
    public Contract contract() {
        return new Contract.Default();
    }

    /**
     * feign日志记录的设置
     * Optional供给，可被动态配置覆盖
     * @return
     */
    /*@Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }*/

    /***
     *  自定义setter工厂，这里可以改变hystrix的配置
     *
     * @return: feign.hystrix.SetterFactory
     */
    @Bean
    public SetterFactory getSetterFactory() {
        return (target, method) -> {
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
