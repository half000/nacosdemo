package com.half.nacos.service;

import com.half.base.bean.Order;

/**
 * @Author: wangwei
 * @Date: 2019-12-09 21:20
 */
public interface OrderService {

    Order get(int orderId);

    Order get2(int orderId);
}
