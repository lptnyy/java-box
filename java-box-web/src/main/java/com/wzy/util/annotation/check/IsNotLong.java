package com.wzy.util.annotation.check;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value= RetentionPolicy.RUNTIME)
public @interface IsNotLong {
    String message();
    int soft() default 2;
}
