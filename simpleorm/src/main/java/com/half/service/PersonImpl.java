package com.half.service;

import com.half.bean.Person;
import com.half.dao.PersonDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author: wangwei
 * @Date: 2020-04-26 16:29
 */
@Service
public class PersonImpl implements PersonSvc {

    @Resource
    private PersonDao personDao;

    @Override
    @Transactional
    public int selectCount() {
        System.out.println("selectCount");
        return personDao.selectCount();
    }

    @Override
    @Transactional
    public Person selectOne(int id) {
        return personDao.selectOne(id);
    }
}
