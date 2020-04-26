package com.half.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 切面
 *
 * @Author: wangwei
 * @Date: 2020-04-22 13:38
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyAspect {

    /**
     * @return the per clause expression, defaults to singleton aspect.
     * Valid values are "" (singleton), "perthis(...)", etc
     */
    public String value() default "";
}

