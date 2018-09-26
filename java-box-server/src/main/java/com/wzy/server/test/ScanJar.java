package com.wzy.server.test;

import com.wzy.server.jar.loader.BoxUrlClassLoader;
import com.wzy.server.jar.loader.config.Jar;

public class ScanJar {

    public static void main(String[] args) throws Exception {
        Jar jarVo = new Jar();
        jarVo.setJarDownUrl("http://freenetfile.oss-ap-southeast-1.aliyuncs.com/java-box-dev.jar");
        BoxUrlClassLoader.scanJar(jarVo);
    }
}
