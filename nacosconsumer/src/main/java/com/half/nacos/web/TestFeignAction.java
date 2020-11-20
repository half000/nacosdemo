package com.half.nacos.web;

import com.half.base.feign.ITestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * feign客户端测试  不带histrix的
 * @Author: wangwei
 * @Date: 2019-11-26 21:38
 */
@RestController
@RequestMapping("feign/test")
public class TestFeignAction {

    @Autowired
    ITestController ITestController;

    @GetMapping("{id}")
    public String getTest(@PathVariable int id) {
        String name="wangwei";
        String note="note";
        Map<String,Object> header=new HashMap<>(8);
        header.put("id",id);
        header.put("name",name);
        Map<String,Object> param=new HashMap<>(8);
        param.put("pa1",id);
        param.put("pa2",name);
        Map<String,Object> body=new HashMap<>(8);
        body.put("key1","value1");
        body.put("key2","value2");
        body.put("key3","value3");


        switch (id) {
            case 0:
                return ITestController.get1(id,name);
            case 1:
                return ITestController.get2(id,name,name);
            case 10:
                return ITestController.post(header,param,body);

            case 20:
                return ITestController.put(id,name,note,body);

            case 30:
                return ITestController.delete(id);

            default:
        }


        return null;
    }

}
