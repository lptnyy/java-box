package com.wzy.server.jar.loader;

import com.wzy.server.jar.loader.config.Jar;
import sun.misc.ClassLoaderUtil;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 动态加载JAR
 */
public class BoxUrlClassLoader {

    // 存放Jar加载集合
    static Map<String, Jar> jarmaps = new HashMap<String, Jar>();

    /**
     * 加载Jar包
     * @param jarVo
     * @return
     * @throws Exception
     */
    public synchronized static boolean addJar(Jar jarVo) throws Exception{
        Jar oldJarVo = getJar(jarVo.getHttpUrl());
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
    public synchronized static boolean removeJar(Jar jarVo) throws Exception{
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
    public static Jar getJar(String key) throws Exception{
        return jarmaps.get(key);
    }

    /**
     * 获取所有已经加载的jar
     * @return
     */
    public static List<Jar> getJarmaps(){
        List<Jar> jars = new ArrayList<>();
        jarmaps.forEach((k,v) ->{
            jars.add(v);
        });
        return jars;
    }
}
