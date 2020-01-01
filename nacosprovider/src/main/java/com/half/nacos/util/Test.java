package com.half.nacos.util;

import java.lang.reflect.Proxy;


public class Test {

    /**
     *
     * @Param args:
     * @Param aa:
     * @return: void
     */
    public static void main(String[] args, String aa) {
        OrderInfo obj=new OrderInfo();
        OrderInfoInter proxy = (OrderInfoInter) Proxy.newProxyInstance(obj.getClass().getClassLoader(), new Class[]{OrderInfoInter.class}, new OrderInfoHandle<OrderInfoInter>(obj));
        proxy.getOrderInfo();
    }
}
