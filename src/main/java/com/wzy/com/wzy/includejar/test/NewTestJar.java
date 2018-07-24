package com.wzy.com.wzy.includejar.test;

import com.wzy.com.wzy.includejar.introduce.BoxUrlClassLoader;
import com.wzy.com.wzy.includejar.introduce.vo.JarVo;

public class NewTestJar {

    /**
     * 主方法
     * @param args
     */
    public static void main(String[] args) throws Exception {
        JarVo jarVo = new JarVo();
        jarVo.setClassName("com.wzy.com.wzy.includejar.test.TestJar");
        jarVo.setJarDownUrl("https://freenetfile.oss-ap-southeast-1.aliyuncs.com/JavaBox.jar");
        jarVo.setHttpUrl("/test");
        BoxUrlClassLoader.addJar(jarVo);
        BoxUrlClassLoader.getJar(jarVo.getHttpUrl());
        BoxUrlClassLoader.removeJar(jarVo);
    }
}
