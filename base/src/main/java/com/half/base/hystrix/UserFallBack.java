package com.half.base.hystrix;

import com.half.base.bean.User;
import com.half.base.feign.IUserController;

/**
 * userController的回调
 * @Author: wangwei
 * @Date: 2019-12-09 20:50
 */
public class UserFallBack implements IUserController {
    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public int add(User user) {
        return -1;
    }

    @Override
    public User update(Integer id, User user) {
        return null;
    }

    @Override
    public User delete(int id) {
        return null;
    }
}
