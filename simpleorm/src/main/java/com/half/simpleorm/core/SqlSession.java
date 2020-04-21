package com.half.simpleorm.core;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Proxy;

/**
 * 模仿mybastis的orm框架
 *
 * @Author: wangwei
 * @Date: 2020-04-11 19:29
 */


@AllArgsConstructor
@Data
public class SqlSession {

    private final Configuration configuration;

    private final Executor executor = new SimpleExecutor();


    public <T> T getMapper(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new MapperProxy(this, clazz));
    }

    public <T> T selectOne(String statement, Object... parameter) {
        return executor.exec(statement, parameter);
    }

}
