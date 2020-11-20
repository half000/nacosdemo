package com.half.nacos.hystrix;

import com.netflix.hystrix.*;

/**
 * Hystrix例子 原生模式
 *
 * @Author: wangwei
 * @Date: 2019-12-07 9:33
 */
public class CommandHelloWorld extends HystrixCommand<String> {

    private final String name;

    public CommandHelloWorld(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionTimeoutEnabled(true)
                        .withExecutionTimeoutInMilliseconds(600000)
                        .withExecutionIsolationThreadInterruptOnTimeout(false))

                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        .withCoreSize(1)
                        .withMaximumSize(2))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("test"))
        )
        ;
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        Thread.sleep(500);
        System.out.println("CommandHelloWorld run");
        return "Hello " + name;
    }

    @Override
    protected String getFallback() {
        System.out.println("CommandHelloWorld fallback");
        return "fallback";
    }
}
