package com.wzy.func.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value= RetentionPolicy.RUNTIME)
public @interface BoxPlug {
    String name() default "";
    String asName() default "";
    int type() default -1;
 }
