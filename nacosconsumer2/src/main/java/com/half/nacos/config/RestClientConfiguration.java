package com.half.nacos.config;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: wangwei
 * @Date: 2019-11-18 22:59
 */
@Configuration
public class RestClientConfiguration {

    /**
     * restTemplate客户端 可以通过设置不同的requestFactory调用HttpClient或者OKHttp等
     * 这里默认是通过设置拦截器的方式引入loadBanlancer，只用了Ribbon的loadBanlancer功能
     * 如果要用Ribbon的config，则需要通过设置RibbonClientHttpRequestFactory的方式,此方式已经弃用
     *
     * {@link RibbonAutoConfiguration}
     *
     * @return: org.springframework.web.client.RestTemplate
     */
    @Bean
    @LoadBalanced
    @SentinelRestTemplate
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory=new SimpleClientHttpRequestFactory();
        simpleClientHttpRequestFactory.setConnectTimeout(10000);
        simpleClientHttpRequestFactory.setReadTimeout(30000);
        return new RestTemplate(simpleClientHttpRequestFactory);
    }


}
