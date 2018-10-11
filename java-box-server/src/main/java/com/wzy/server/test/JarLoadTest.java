package com.wzy.server.test;

import com.wzy.server.config.Config;
import com.wzy.server.jar.loader.LoadJar;
import com.wzy.server.jar.loader.LoadJarImpl;

public class JarLoadTest {

    public static void main(String[] args) throws Exception {
        Config.initConfig();
        LoadJar loadJar = new LoadJarImpl();
        loadJar.initHttp();
        loadJar.initJar();
    }
}
