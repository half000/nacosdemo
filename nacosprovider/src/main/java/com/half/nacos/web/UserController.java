package com.half.nacos.web;

import com.alibaba.fastjson.JSON;
import com.half.base.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: wangwei
 * @Date: 2019-11-30 9:15
 */
@RestController
@RequestMapping("user")
@Slf4j
@Api(value = "用户controller", tags = {"用户操作接口"})
public class UserController {

    private Map<Integer, User> userMap = new HashMap<>(16);
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    {
        userMap.put(10000, new User(10000, "wangwei", true));
        userMap.put(10001, new User(10001, "wangwei", true));
    }

    @ApiOperation(value = "获取用户信息", httpMethod = "GET")
    @GetMapping("{id}")
    public User get(@ApiParam(name = "id", value = "用户ID") @PathVariable int id) {
        User user = userMap.get(id);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("id={},user={}", id, JSON.toJSON(user));
        return user;
    }

    @ApiOperation(value = "新增用户", httpMethod = "POST")
    @PostMapping
    public int add(@ApiParam(name = "user", value = "用户信息") @RequestBody User user) {
        int id = atomicInteger.incrementAndGet();
        log.info("id={},user={}", id, JSON.toJSON(user));
        userMap.put(id, user);
        return id;
    }

    @ApiOperation(value = "修改用户", httpMethod = "PUT")
    @PutMapping("{id}")
    public User update(@ApiParam(name = "id", value = "id") @PathVariable Integer id, @ApiParam(name = "user", value = "用户信息") @RequestBody User user) {
        User old = userMap.get(id);
        log.info("old={}", JSON.toJSON(old));
        userMap.put(id, user);
        return old;
    }

    @ApiOperation(value = "删除用户", httpMethod = "DELETE")
    @DeleteMapping("{id}")
    public User delete(@ApiParam(name = "id", value = "id") @PathVariable int id) {
        User old=userMap.get(id);
        log.info("old={}",JSON.toJSONString(old));
        userMap.remove(id);
        return old;
    }

}
