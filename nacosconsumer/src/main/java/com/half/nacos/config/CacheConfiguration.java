package com.half.nacos.config;

import com.half.nacos.interceptor.CacheContextInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.Controller;

/**
 * 拦截器配置
 *
 * 将 Interceptor 注册到Spring MVC 控制器
 */
@Configuration
public class CacheConfiguration {

    /**
     * 声明一个 cacheContextInterceptor 注入 Spring 容器
     */
    @Bean
    @ConditionalOnClass(Controller.class)
    public CacheContextInterceptor cacheContextInterceptor(){
        return new CacheContextInterceptor();
    }

    @Configuration
    @ConditionalOnClass(Controller.class)
    public class WebMvcConfig implements WebMvcConfigurer {

        private final CacheContextInterceptor interceptor;

        @Autowired
        public WebMvcConfig(CacheContextInterceptor interceptor) {
            this.interceptor = interceptor;
        }

        /**
         * 将 cacheContextInterceptor 添加到拦截器中
         */
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(interceptor);
        }
    }

}