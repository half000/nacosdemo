package com.half.nacos.web;

import com.half.base.bean.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: wangwei
 * @Date: 2019-12-09 21:29
 */
@RestController
@Slf4j
@RequestMapping("order")
public class OrderController {

    private ConcurrentHashMap<Integer, Order> orders = new ConcurrentHashMap();

    {
        orders.put(10000, new Order(10000, 10000, "wangwei", true));
        orders.put(10001, new Order(10001, 10000, "wangwei", true));
    }

    @GetMapping("{orderId}")
    public Order getOrder(@PathVariable int orderId) {
        Random random = new Random();

        try {
            Thread.sleep(random.nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return orders.get(orderId);
    }
}
