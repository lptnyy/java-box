package com.wzy.server.jar.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value= RetentionPolicy.RUNTIME)
public @interface BoxModule {
    String moduleName() default "";
    String moduleRoute() default "";
}
