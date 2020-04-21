package com.half.simpleorm.core;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @Author: wangwei
 * @Date: 2020-04-11 19:34
 */
public interface Executor {

    public <T> T exec(String statement, Object parameter);
}
