package com.half.nacos.web;

import com.half.base.bean.Order;
import com.half.nacos.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * @Author: wangwei
 * @Date: 2019-12-09 21:49
 */
@RestController
@RequestMapping("order")
public class OrderAction {

    @Resource(name= "orderServiceImpl")
    private OrderService orderService;

    @Autowired
    @Qualifier("orderService2Impl")
    private OrderService orderService2;

    private ExecutorService executorService= new ThreadPoolExecutor(2, 20, 60,
            TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), (r)->{
        Thread thread=new Thread(r);
        thread.setName("order-pool");
        return thread;
    })
       ;

    @GetMapping("get")
    public Order get(){
        return orderService.get(10000);
    }

    @GetMapping("get1")
    public Order get1(){
        System.out.println("调用");
        orderService.get2(10000).consumer(System.out::println);
        System.out.println("调用，走缓存");
        orderService2.get2(10000).consumer(System.out::println);
        orderService2.get2(10001).consumer(System.out::println);
        System.out.println("调用，走缓存");
        return orderService.get2(10001).apply(System.out::println);
    }

    @GetMapping("gets")
    public String get(@RequestParam(defaultValue = "100") int count){
       // Random random=new Random();
        IntStream.range(0,count).parallel().mapToObj(i-> {
                    try {
                        return executorService.submit(() -> orderService.get(10000)).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    return null;
                }

         ).forEach(System.out::println);

        return "success";
    }
}
