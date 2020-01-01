package com.half.nacos.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: wangwei
 * @Date: 2019-11-24 17:08
 */
@Data
public class UserBean {

    @ApiModelProperty(example = "100")
    private int id;
    private String name;


}
