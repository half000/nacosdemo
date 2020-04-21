package com.half.simpleorm.demo;

import com.half.simpleorm.core.Configuration;
import com.half.simpleorm.core.SqlSession;

/**
 * @Author: wangwei
 * @Date: 2020-04-11 22:38
 */
public class TestMain {
    public static void main(String[] args) {
        new SqlSession(new Configuration()).getMapper(TestMapper.class).selectByPrimaryKey(111);
    }
}
