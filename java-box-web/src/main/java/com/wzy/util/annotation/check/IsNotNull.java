package com.wzy.util.annotation.check;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value= RetentionPolicy.RUNTIME)
public @interface IsNotNull {
    String message();
    int soft() default 1;
}
