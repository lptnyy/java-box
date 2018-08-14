package com.wzy.jar.loader;

import com.wzy.jar.loader.vo.JarVo;
import sun.misc.ClassLoaderUtil;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态加载JAR
 */
public class BoxUrlClassLoader {

    // 存放Jar加载集合
    static Map<String, JarVo> jarmaps = new HashMap<String, JarVo>();

    /**
     * 加载Jar包
     * @param jarVo
     * @return
     * @throws Exception
     */
    public synchronized static boolean addJar(JarVo jarVo) throws Exception{
        JarVo oldJarVo = getJar(jarVo.getHttpUrl());
        if (oldJarVo != null) {
            if (jarVo.getJarMd5().equals(oldJarVo.getJarMd5())) {
                return true;
            } else {
                removeJar(jarVo);
            }
        }
        URL url = new URL(jarVo.getJarDownUrl());
        URLClassLoader myClassLoader = new URLClassLoader( new URL[] { url } );
        jarVo.setClassLoader(myClassLoader);
        jarmaps.put(jarVo.getHttpUrl(), jarVo);
        return true;
    }

    /**
     * 释放Jar
     * @param jarVo
     * @return
     * @throws Exception
     */
    public synchronized static boolean removeJar(JarVo jarVo) throws Exception{
        jarmaps.remove(jarVo.getHttpUrl());
        jarVo.getClassLoader().close();
        ClassLoaderUtil.releaseLoader(jarVo.getClassLoader());
        return true;
    }

    /**
     * 获取Jar信息
     * @param key
     * @return
     */
    public static JarVo getJar(String key) throws Exception{
        return jarmaps.get(key);
    }
}
