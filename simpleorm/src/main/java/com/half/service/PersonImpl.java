package com.half.service;

import com.half.dao.Person;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: wangwei
 * @Date: 2020-04-26 16:29
 */
@Service
public class PersonImpl implements PersonSvc {

    @Resource
    private Person person;

    @Override
    public int selectCount() {
        return person.selectCount();
    }

    @Override
    public Person selectOne(int id) {
        return person.selectOne(id);
    }
}
