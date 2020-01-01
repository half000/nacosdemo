package com.half.nacos.interceptor;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * hystrix 请求缓存上下文拦截器
 */
@Slf4j
public class CacheContextInterceptor implements HandlerInterceptor {

    private HystrixRequestContext context;

    /**
     * 请求前
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("===call trace-1===");
        context = HystrixRequestContext.initializeContext();
        return true;
    }

    /**
     * 请求
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 请求后
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        context.shutdown();
    }
}