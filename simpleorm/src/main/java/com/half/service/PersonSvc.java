package com.half.service;

import com.half.bean.Person;

/**
 * @Author: wangwei
 * @Date: 2020-04-26 16:29
 */
public interface PersonSvc {

    int selectCount();

    Person selectOne(int id);

}
