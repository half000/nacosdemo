package com.half.controller;

import com.half.service.PersonSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wangwei
 * @Date: 2020-04-26 16:32
 */
@RestController
@RequestMapping("test")
public class PersonAction {

    @Autowired
    private PersonSvc personSvc;

    @RequestMapping("count")
    public String count() {
        return "" + personSvc.selectCount();
    }

    @RequestMapping("search")
    public String selectOne() {
        return "" + personSvc.selectOne(1);
    }
}
