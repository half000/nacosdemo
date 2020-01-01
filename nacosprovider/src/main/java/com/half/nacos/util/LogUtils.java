package com.half.nacos.util;

import org.slf4j.Logger;

import java.util.Map;
import java.util.stream.Stream;

/**
 * @Author: wangwei
 * @Date: 2019-11-24 10:43
 */
public class LogUtils {

    public  static  <K,V> void info(Logger log, Map<K,V> param,String ...strs){
        Stream.of(strs).forEach(log::info);
        param.entrySet().stream().map(stringStringEntry -> stringStringEntry.getKey()+","+stringStringEntry.getValue())
                .forEach(log::info);
    }
}
