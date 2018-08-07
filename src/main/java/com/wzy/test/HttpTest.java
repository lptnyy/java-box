package com.wzy.test;

import com.wzy.server.jar.LoadJar;
import com.wzy.server.config.Config;
import com.wzy.server.jar.loader.BoxUrlClassLoader;
import com.wzy.server.jar.loader.vo.JarVo;

public class HttpTest {

    public static void main(String[] args) throws Exception {
        Config.initConfig();
        LoadJar jarUtil = new LoadJar();
        jarUtil.initHttp();
        jarUtil.initJar();
        JarVo jarVo = BoxUrlClassLoader.getJar("/test/user");
        Class class1 = jarVo.getClassLoader().loadClass("com.wzy.com.wzy.includejar.test.TestJar");
        System.out.println(class1.getName());
        BoxUrlClassLoader.removeJar(jarVo);
        class1 = jarVo.getClassLoader().loadClass("com.wzy.com.wzy.includejar.test.TestJar");
        System.out.println(class1.getName());
    }
}
