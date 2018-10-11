package com.wzy.server.jar.loader;

import com.wzy.server.config.Config;
import com.wzy.server.jar.annotation.BoxApi;
import com.wzy.server.jar.annotation.BoxApp;
import com.wzy.server.jar.api.config.BoxAppApi;
import com.wzy.server.jar.loader.config.Jar;
import com.wzy.server.jar.loader.config.ScanJar;
import sun.misc.ClassLoaderUtil;

import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 动态加载JAR
 */
public class BoxUrlClassLoader {

    // 存放Jar加载集合
    static Map<String, Jar> jarmaps = new HashMap<String, Jar>();
    static Map<Integer, String> jarIdToMd5Map = new HashMap<>();

    /**
     * 加载Jar包
     * @param jarVo
     * @return
     * @throws Exception
     */
    public synchronized static boolean addJar(Jar jarVo) throws Exception{
        URL url = new URL(Config.config.getJarDownServerUrl()+jarVo.getJarDownUrl());
        URLClassLoader myClassLoader = new URLClassLoader( new URL[] { url } );
        jarVo.setClassLoader(myClassLoader);
        jarmaps.put(jarVo.getJarMd5(), jarVo);
        jarIdToMd5Map.put(jarVo.getBaseId(), jarVo.getJarMd5());
        return true;
    }

    public synchronized static ScanJar scanJar(Jar jarVo) throws Exception{
        URL url = new URL("jar:"+jarVo.getJarDownUrl()+"!/");
            JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
            JarFile jarFile = jarURLConnection.getJarFile();
            Enumeration enu = jarFile.entries();
            Map<String, Object> classMap = new HashMap<>();
            while (enu.hasMoreElements()) {
                JarEntry jarEntry = (JarEntry) enu.nextElement();
                String name = jarEntry.getName();
                if (name.endsWith(".class")) {
                    System.out.println("name=" + name);
                    String value = name.replace("/",".");
                    classMap.put(name, value.substring(0, value.lastIndexOf(".")));
                }
        }
        ScanJar scanJar = new ScanJar();
        List<com.wzy.server.jar.api.config.BoxApp> boxProjectVos = new ArrayList<>();
        List<BoxAppApi> boxApiVos = new ArrayList<>();

        URLClassLoader myClassLoader = new URLClassLoader( new URL[] { url } );

        for(Object v: classMap.values()) {

            try {
                Class classObj = myClassLoader.loadClass(v.toString());

                // 获取类标记的注解信息
                BoxApp boxAppAn = (BoxApp) classObj.getAnnotation(BoxApp.class);
                if (boxAppAn != null) {

                    // 封装项目信息
                    com.wzy.server.jar.api.config.BoxApp boxProjectVo = new com.wzy.server.jar.api.config.BoxApp();
                    boxProjectVo.setName(boxAppAn.name());
                    boxProjectVo.setRoute(boxAppAn.route());
                    boxProjectVos.add(boxProjectVo);

                    // 循环获取类中的方法
                    Method[] publicMethod = classObj.getMethods();
                    for (Method method : publicMethod) {

                        // 获得方法标记的注解信息
                        BoxApi boxApi = method.getAnnotation(BoxApi.class);
                        if (boxApi != null) {
                            // 封装接口访问信息
                            BoxAppApi boxApiVo = new BoxAppApi();
                            boxApiVo.setName(boxApi.name());
                            boxApiVo.setRoute(boxApi.route());
                            boxApiVo.setRunClass(v.toString());
                            boxApiVo.setLinkUrl(boxProjectVo.getRoute());
                            boxApiVo.setRunFunction(method.getName());
                            boxApiVos.add(boxApiVo);
                        }
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        // 释放jar
        myClassLoader.close();
        ClassLoaderUtil.releaseLoader(myClassLoader);
        jarFile.close();

        // 封装jar获取的注解信息 以及反馈
        scanJar.setBoxApiVoList(boxApiVos);
        scanJar.setBoxProjectVo(boxProjectVos);
        return scanJar;
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
    public static synchronized List<Jar> getJarmaps(){
        List<Jar> jars = new ArrayList<>();
        jarmaps.forEach((k,v) ->{
            jars.add(v);
        });
        return jars;
    }
}
