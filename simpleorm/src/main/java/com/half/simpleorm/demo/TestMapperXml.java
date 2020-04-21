package com.half.simpleorm.demo;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wangwei
 * @Date: 2020-04-11 22:21
 */
@Data
public class TestMapperXml {
    public static final String nameSpace = "com.half.simpleorm.demo.TestMapper";
    public static final Map<String, String> mapping = new HashMap();

    static {
        mapping.put("selectByPrimaryKey", "select * from t_t1 where id=%d");
    }

}
