package com.half.nacos.client;

import com.alibaba.fastjson.JSON;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import org.springframework.cloud.client.loadbalancer.LoadBalancerRequest;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;

import java.io.IOException;

/**
 * 其实的rpc与ribbon结合例子
 * 1.创建一个ice 相关的 lb客户端
 * 2.使用的时候就直接用这个客户端来访问ice
 * 3.在这个客户端里调用lb来选择服务器，并发起访问
 * @Author: wangwei
 * @Date: 2020-11-17 14:47
 */

public class IceLoadBalancerClient implements IceLoadBalancer {

    private SpringClientFactory clientFactory;

    public IceLoadBalancerClient(SpringClientFactory clientFactory){
        this.clientFactory=clientFactory;
    }

    @Override
    public <T> T execute(String serviceId, LoadBalancerRequest<T> request) throws IOException {
        System.out.println("根据serviceId，来选择实际的endpoint执行请求");
        //可以扩展不同的lb
        ILoadBalancer loadBalancer = this.clientFactory.getLoadBalancer(serviceId);

        Server server = loadBalancer.chooseServer("default");
        if (server == null) {
            throw new IllegalStateException("No instances available for " + serviceId);
        }

        System.out.println(JSON.toJSONString(server));
        //根据endpoint获取ice代理，这里缓存一下
        System.out.println("实际调用ice接口");
        return null;
    }

}
