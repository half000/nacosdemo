package com.half.base.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.function.Consumer;

/**
 * @Author: wangwei
 * @Date: 2019-12-09 21:15
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Slf4j
@ApiModel(value = "Order",description = "订单Bean")
public class Order implements Cloneable{

    @ApiModelProperty(value="订单id",example = "10000",required = true)
    @NonNull
    private int orderId;

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

    @ApiModelProperty("延迟时间")
    private long delay;

    public void consumer(Consumer  consumer){
        consumer.accept(this);
    }

    public Order apply(Consumer  consumer){
        consumer.accept(this);
        return this;
    }

    @Override
    public Order clone()  {
        try {
            return (Order) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return new Order();
    }
}
