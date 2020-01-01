package com.half.nacos.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.half.base.bean.Order;
import com.half.nacos.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Author:  wangwei
 * @Date: 2019-12-09 21:15
 */
@Service

public class OrderService2Impl implements OrderService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 线程池隔离
     *
     * @param orderId
     * @return
     */
    @Override
    @SentinelResource("OrderService2Impl.get")
    public Order get(int orderId) {
        return restTemplate.getForObject("http://nacosprovider/order/" + orderId, Order.class);
    }

    /**
     * 信号量隔离
     *
     * @param orderId
     * @return
     */
    @Override
    @SentinelResource("OrderService2Impl.get2")
    public Order get2(int orderId) {
        System.out.println("OrderServiceImp2执行");
        return restTemplate.getForObject("http://nacosprovider/order/" + orderId, Order.class);
    }

    public Order getFallBack(int orderId) {
        return new Order(1, 1, "降级", true);
    }

    public Order fallBack() {
        return new Order(1, 1, "降级", true);
    }
}
