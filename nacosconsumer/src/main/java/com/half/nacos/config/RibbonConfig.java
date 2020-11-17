package com.half.nacos.config;

import feign.Request;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClientConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 自定义ribbon配置类 以@RibbonClient注解中的属性命名（nacosprovider）
 * Nacos与Ribbon都有注册相关的配置类，以default开头命名
 * Nacos自带的配置类（default.com.alibaba.cloud.nacos.ribbon.RibbonNacosAutoConfiguration）如下，
 *
 * @see com.alibaba.cloud.nacos.ribbon.RibbonNacosAutoConfiguration 里(@RibbonClients(defaultConfiguration = NacosRibbonClientConfiguration.class))
 * <p>
 * Ribbon自带的配置类（default.org.springframework.cloud.netflix.ribbon.RibbonAutoConfiguration）如下
 * @see org.springframework.cloud.netflix.ribbon.RibbonAutoConfiguration
 * <p>
 * SpringClientFactory 自包含了Map<String, AnnotationConfigApplicationContext> contexts
 * @see org.springframework.cloud.context.named.NamedContextFactory
 * SpringClientFactory作为Ribbon相关组件的上下文工厂，feign也用的类似的技术
 * <p>
 *
 * 关键桥梁类是 LoadBalancerClient  连接了loadbalancer相关的组件对象（loadbalancer支持的各种属性）
 * 与 SpringClientFactory 自动化配置相关的类,其实默认配置类如下
 * @see RibbonClientConfiguration
 * 并由它提供对外的方法调用支持
 * <p>
 * 如果对其他的rpc扩展使用ribbon，就需要把loadBlancerClient给注入进去，访问serviceInstance时，自动选择实际的endpoint
 * <p>
 * 如果更换其他的注册中心或者其他的发现客户端模式，则需要扩展
 * @see com.netflix.loadbalancer.ServerList
 *
 *
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
