package com.half.nacos.web;

import com.alibaba.fastjson.JSON;
import com.half.base.bean.User;
import com.half.base.feign.IUserController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * @Author: wangwei
 * @Date: 2019-11-30 8:55
 */
@RestController
@RequestMapping("feign/user")
@Slf4j
public class UserAction {

    @Resource
    private IUserController iUserController;

    private static int TYPE_ADD=1;


    @GetMapping("{type}")
    public String getTest(@PathVariable int type,
                          @RequestParam(required = false,defaultValue = "1") int id,
                          @RequestParam(required = false,defaultValue = "666") int userId,
                          @RequestParam(required = false,defaultValue = "wangwei") String name) {
        User user=new User(userId,name,true);
        User rtnUser=null;
        int newId=0;
        switch(type){
            case 0:
                rtnUser=iUserController.get(id);
                break;
            case 1:
                newId=iUserController.add(user);
                break;
            case 2:
                rtnUser=iUserController.update(id,user);
                break;
            case 3:
                rtnUser=iUserController.delete(id);
                break;
            default:

        }
        log.info("dddd1");
        new Thread(()->log.info("dddd2")).start();

        if(type== TYPE_ADD){
            return ""+newId;
        }
        return JSON.toJSONString(rtnUser);
    }
}
