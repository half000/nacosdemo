package com.half.simpleorm.core;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: wangwei
 * @Date: 2020-04-11 22:33
 */
@Slf4j
public class SimpleExecutor implements Executor {
    @Override
    public <T> T exec(String statement, Object parameter) {
        log.info(statement, parameter);
        //取连接访问数据库
        return null;
    }
}
