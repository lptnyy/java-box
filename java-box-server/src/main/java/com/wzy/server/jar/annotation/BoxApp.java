package com.wzy.server.jar.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value= RetentionPolicy.RUNTIME)
public @interface BoxApp {
    String name() default "";
    String route() default "";
}
