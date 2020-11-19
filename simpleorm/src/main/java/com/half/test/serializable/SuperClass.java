package com.half.test.serializable;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: wangwei
 * @Date: 2020-04-30 13:56
 */
@Data
public class SuperClass implements Serializable {
    private int age;
    private String name;

}
