package com.half.base.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 *
 * @Author: wangwei
 * @Date: 2019-11-30 8:18
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Slf4j
@ApiModel(value = "User",description = "用户Bean")
public class User {
    @ApiModelProperty(value="用户id",example = "10000",required = true)
    @NonNull
    private int userId;

    @ApiModelProperty(value="用户名",required = true)
    @NonNull
    private String userName;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty(value="状态",required = true,example = "true")
    @NonNull
    private boolean status;
}
