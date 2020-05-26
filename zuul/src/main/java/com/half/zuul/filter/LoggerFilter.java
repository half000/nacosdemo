package com.half.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
@Slf4j
public class LoggerFilter extends ZuulFilter {


    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String method = request.getMethod();
        Map<String, String[]> params = request.getParameterMap();
        String paramsStr = params.toString();
        long statrtTime = (long) context.get("startTime");
        Throwable throwable = context.getThrowable();
        String uri = request.getRequestURI();
        String remoteAddr = request.getRemoteAddr();
        //context.getResponseStatusCode();
        long duration = System.currentTimeMillis() - statrtTime;
        log.info("uri={},duration={},remoteAddr={},method={},paramsStr={},throwable={}", uri, duration, remoteAddr, method, paramsStr, throwable);
        return null;
    }

}