package com.half.dao;

import org.apache.ibatis.annotations.Select;

/**
 * @Author: wangwei
 * @Date: 2020-04-26 16:18
 */
public interface Person {

    /**
     * 查询行数
     *
     * @return
     */
    @Select("select count(1) from t_person")
    int selectCount();

    @Select("select * from t_person where id= #{id}")
    Person selectOne(int id);
}
