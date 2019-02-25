package com.wzy.box.server.util;

import org.springframework.context.ConfigurableApplicationContext;

public class BoxApplicationContext {
    public static ConfigurableApplicationContext applicationContext;
    public static ConfigurableApplicationContext getApplicationContext() {
        return applicationContext;
    }
    public static void setApplicationContext(ConfigurableApplicationContext applicationContext) {
        BoxApplicationContext.applicationContext = applicationContext;
    }
}
