package com.half.simpleorm.core;


import com.half.simpleorm.demo.TestMapperXml;
import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: wangwei
 * @Date: 2020-04-11 21:32
 */
@AllArgsConstructor
public class MapperProxy<T> implements InvocationHandler {

    private final SqlSession sqlSession;
    private final Class<T> mapperInterface;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //sqlSession.getConfiguration().
        if (TestMapperXml.nameSpace.equals(method.getDeclaringClass().getName())) {
            return sqlSession.selectOne(TestMapperXml.mapping.get(method.getName()), args);
        }
        return null;
    }
}
