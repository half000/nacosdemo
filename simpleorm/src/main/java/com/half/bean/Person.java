package com.half.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: wangwei
 * @Date: 2020-04-26 16:25
 */
@Data
@AllArgsConstructor
public class Person {
    private int id;
    private String name;
    private int age;
}
