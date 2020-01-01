package com.half.nacos.service.impl;

import com.half.base.bean.Order;
import com.half.nacos.service.OrderService;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import com.netflix.hystrix.strategy.properties.archaius.HystrixDynamicPropertiesArchaius;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author  wangwei
 * @Date  2019-12-09 21:15
 */
@Service
//共同属性
@DefaultProperties(defaultFallback = "fallBack",
        commandProperties = {
                /**
                 *  参考以下文件
                 *  {@link HystrixPropertiesManager}
                 *  {@link HystrixCommandProperties}
                 *  {@link HystrixThreadPoolProperties}
                 *  {@link HystrixDynamicPropertiesArchaius}
                 *
                 *  这里是一个整体，不会单条属性覆盖
                 */
                //Command execution properties.
                @HystrixProperty(name = "execution.timeout.enabled", value = "true"),
                //Command fallback properties.
                @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "5"),
                //Command circuit breaker properties.
                @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "20"),
                @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
                //Command metrics properties.
                //Command CommandRequest Context properties.
                @HystrixProperty(name = "requestCache.enabled", value = "true"),

                //Collapser properties.
        },
        threadPoolProperties = {
                //Thread pool properties.
                @HystrixProperty(name = "coreSize", value = "3"),


        }
)
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 线程池隔离
     *
     * @param orderId
     * @return
     */
    @Override
    @HystrixCommand(threadPoolKey = "hystrix-OrderService", fallbackMethod = "getFallBack",
            commandProperties = {@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
                    @HystrixProperty(name = "execution.isolation.thread.interruptOnTimeout", value = "1000"),
                    @HystrixProperty(name = "execution.isolation.thread.interruptOnFutureCancel", value = "false")

            })
    public Order get(int orderId) {
        return restTemplate.getForObject("http://nacosprovider/order/" + orderId, Order.class);
    }

    /**
     * 信号量隔离
     *
     * @param orderId
     * @return
     */
    @CacheResult
    @HystrixCommand(threadPoolKey = "hystrix-OrderService",
            commandProperties = {@HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"),
                    @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests", value = "5"),
                    @HystrixProperty(name = "execution.timeout.enabled", value = "false")
            })
    @Override
    public Order get2(@CacheKey int orderId) {
        System.out.println("OrderServiceImp执行");
        return restTemplate.getForObject("http://nacosprovider/order/" + orderId, Order.class);
    }

    public Order getFallBack(int orderId) {
        return new Order(1, 1, "降级", true);
    }

    public Order fallBack() {
        return new Order(1, 1, "降级", true);
    }
}
