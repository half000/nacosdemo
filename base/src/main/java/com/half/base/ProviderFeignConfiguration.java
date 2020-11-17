package com.half.base;

import feign.ReflectiveFeign;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * 通过注册器导入 FeignClientFactoryBean 与 自定义FeignClientSpecification配置
 *
 * @Author: wangwei
 * @Date: 2019-11-30 13:25
 * @see org.springframework.cloud.openfeign.FeignClientsRegistrar
 * @see org.springframework.cloud.openfeign.FeignClientFactoryBean#getObject()
 * FeignClientFactoryBean创建feign客户端，首先获取FeignContext，然后通过FeignContext来创建Feign.Builder
 * （也是个上下文，默认配置
 * @see FeignClientsConfiguration ，和ribbon的套路一样），
 * 然后builder创建feign
 * @see ReflectiveFeign
 * ，再feign.ReflectiveFeign#newInstance(feign.Target)创建对应的代理类
 * <p>
 * FeignContext由spring上下文管理，可定制化
 * @see FeignAutoConfiguration#feignContext() 这里会把FeignClientSpecification  设置进去
 */
@Configuration
@EnableFeignClients
public class ProviderFeignConfiguration {


}
