package com.half.simpleorm.demo;

/**
 * @Author: wangwei
 * @Date: 2020-04-11 22:15
 */
public interface TestMapper {

    public <T> T selectByPrimaryKey(int id);

}
