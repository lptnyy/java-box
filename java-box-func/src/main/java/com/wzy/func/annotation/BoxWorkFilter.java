package com.wzy.func.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value= RetentionPolicy.RUNTIME)
public @interface BoxWorkFilter {

    // 过滤器名称
    String name() default "";

    // 过滤器过滤地址
    String path() default "";
}
