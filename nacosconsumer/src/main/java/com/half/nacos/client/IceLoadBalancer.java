package com.half.nacos.client;

import org.springframework.cloud.client.loadbalancer.LoadBalancerRequest;

import java.io.IOException;

/**
 * @Author: wangwei
 * @Date: 2020-11-17 16:28
 */
public interface IceLoadBalancer {

    public <T> T execute(String serviceId,LoadBalancerRequest<T>request) throws IOException;
}
