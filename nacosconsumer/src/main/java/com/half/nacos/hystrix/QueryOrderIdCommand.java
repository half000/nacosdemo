package com.half.nacos.hystrix;

import com.netflix.hystrix.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueryOrderIdCommand extends HystrixCommand<Integer> {
    private final static Logger logger = LoggerFactory.getLogger(QueryOrderIdCommand.class);
    private OrderServiceProvider orderServiceProvider;
 
    public QueryOrderIdCommand(OrderServiceProvider orderServiceProvider) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("orderService"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("queryByOrderId"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        //至少有10个请求，熔断器才进行错误率的计算
                        .withCircuitBreakerRequestVolumeThreshold(10)
                        //熔断器中断请求5秒后会进入半打开状态,放部分流量过去重试
                        .withCircuitBreakerSleepWindowInMilliseconds(5000)
                        //错误率达到50开启熔断保护
                        .withCircuitBreakerErrorThresholdPercentage(50)
                        .withExecutionTimeoutEnabled(true))
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties
                        .Setter().withCoreSize(10)));
        this.orderServiceProvider = orderServiceProvider;
    }
 
    @Override
    protected Integer run() {
        return orderServiceProvider.queryByOrderId();
    }
 
    @Override
    protected Integer getFallback() {
        return -1;
    }

    static public class OrderServiceProvider{
        public int queryByOrderId(){
            return 1;
        }
    }
}