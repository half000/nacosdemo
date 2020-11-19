package com.half.test.serializable;

import lombok.Data;
import lombok.ToString;

/**
 * @Author: wangwei
 * @Date: 2020-04-30 13:58
 */
@Data
@ToString(callSuper = true)
public class SubClass extends SuperClass {
    private String shortName;

}
