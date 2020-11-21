package com.half.nacos.web;

import com.half.base.bean.Order;
import com.half.nacos.service.OrderService;
import com.netflix.hystrix.HystrixRequestCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 *  用来测试hystrix的使用
 *  rxjava reactive 事件发布订阅模型，主逻辑在
 * @see com.netflix.hystrix.AbstractCommand#toObservable()
 *  全是查询方法模拟
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

    /**
     * 单个请求
     * @return
     */
    @GetMapping("get")
    public Order get(){
        return orderService.get1(10000);
    }

    /**
     * 缓存测试， 必须同一个comand同一个value才生效
     * 同commandkey，valuekey（@CacheKey）不同，缓存不生效
     *
     * cachekey 为 ValueCacheKey
     * @see HystrixRequestCache.ValueCacheKey  RequestCacheKey+valuekey
     * @see HystrixRequestCache.RequestCacheKey HystrixCommandKey（HystrixCollapserKey）+type+HystrixConcurrencyStrategy
     * @return
     */
    @GetMapping("get1")
    public List<Order> get1(){

        ArrayList<Order> orders = new ArrayList<>();
        System.out.println("调用1");
        orders.add( orderService.get2(10000));
        System.out.println("调用2");
        orders.add( orderService.get2(10000));
        System.out.println("调用3");
        orders.add( orderService.get2(10001));
        System.out.println("调用4");
        orders.add( orderService.get2(10000));
        System.out.println("调用5");
        orders.add( orderService.get2(10001));
        System.out.println("调用6");
        orders.add( orderService.get2(10001));
        return orders;
    }

    /**
     * 缓存测试，不同 commandkey 相同valuekey 缓存不同
     * cachekey 为 ValueCacheKey
     * @see HystrixRequestCache.ValueCacheKey  RequestCacheKey+valuekey
     * @see HystrixRequestCache.RequestCacheKey HystrixCommandKey（HystrixCollapserKey）+type+HystrixConcurrencyStrategy
     * @return
     */
    @GetMapping("get2")
    public List<Order> get2(){

        ArrayList<Order> orders = new ArrayList<>();
        System.out.println("调用1");
        orders.add( orderService.get2(10000));
        System.out.println("调用2");
        orders.add( orderService2.get2(10000));
        System.out.println("调用3");
        orders.add( orderService.get2(10000));
        System.out.println("调用4");
        orders.add( orderService2.get2(10000));
        return orders;
    }

    /**
     * 并发多个请求，用来触发histrix的thread熔断降级
     * @param count
     * @return
     */
    @GetMapping("gets1")
    public String gets1(@RequestParam(defaultValue = "100") int count){
        // Random random=new Random();
        IntStream.range(0,count).parallel().mapToObj(i-> {
                    try {
                        return executorService.submit(() -> orderService.get1(10000)).get();
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

    /**
     * 并发多个请求，用来触发histrix的 semaphore 熔断降级
     * @param count
     * @return
     */
    @GetMapping("gets2")
    public String gets2(@RequestParam(defaultValue = "100") int count){
        // Random random=new Random();
        IntStream.range(0,count).parallel().mapToObj(i-> {
                    try {
                        return executorService.submit(() -> orderService.get2(10000)).get();
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
