package com.wzy.server.jar.loader;

import com.wzy.server.config.Config;
import com.wzy.func.annotation.BoxApi;
import com.wzy.func.annotation.BoxApp;
import com.wzy.func.annotation.BoxWorkFilter;
import com.wzy.server.jar.api.config.BoxAppApi;
import com.wzy.server.jar.api.config.BoxFilter;
import com.wzy.server.jar.loader.config.Jar;
import com.wzy.server.jar.loader.config.ScanJar;
import sun.misc.ClassLoaderUtil;

import java.io.IOException;
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

    // 存放加载过滤器的jar
    static Map<String, Jar> jarFailtermaps = new HashMap<String, Jar>();
    static Map<Integer, String> jarIdFailterMd5Map = new HashMap<>();

    /**
     * 加载Jar包
     * @param jarVo
     * @return
     * @throws Exception
     */
    public synchronized static boolean addJar(Jar jarVo) throws Exception{
        if (jarmaps.get(jarVo.getJarMd5()) != null) {
            return true;
        }
        URL url = new URL(Config.config.getJarDownServerUrl()+jarVo.getJarDownUrl());
        URLClassLoader myClassLoader = new URLClassLoader( new URL[] { url } );
        jarVo.setClassLoader(myClassLoader);
        jarmaps.put(jarVo.getJarMd5(), jarVo);
        jarIdToMd5Map.put(jarVo.getBaseId(), jarVo.getJarMd5());
        return true;
    }

    /**
     * 加载Jar包
     * @param jarVo
     * @return
     * @throws Exception
     */
    public synchronized static boolean addFliterJar(Jar jarVo) throws Exception{
        if (jarFailtermaps.get(jarVo.getJarMd5()) != null) {
            return true;
        }
        URL url = new URL(Config.config.getJarDownServerUrl()+jarVo.getJarDownUrl());
        URLClassLoader myClassLoader = new URLClassLoader( new URL[] { url } );
        jarVo.setClassLoader(myClassLoader);
        jarFailtermaps.put(jarVo.getJarMd5(), jarVo);
        jarIdFailterMd5Map.put(jarVo.getBaseId(), jarVo.getJarMd5());
        return true;
    }

    /**
     * 获取要执行的jar
     * @return
     */
    public static Jar getFilterJar(String md5){
        return jarFailtermaps.get(md5);
    }

    /**
     * 卸载过滤器
     * @param id
     * @return
     * @throws IOException
     */
    public synchronized static boolean removeFliterJar(Integer id) throws IOException {
        String jarMd5 = jarIdFailterMd5Map.get(id);
        jarIdFailterMd5Map.remove(id);
        Jar jar = jarFailtermaps.get(jarMd5);
        jar.getClassLoader().close();
        ClassLoaderUtil.releaseLoader(jar.getClassLoader());
        jarFailtermaps.remove(jarMd5);
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
        List<BoxFilter> boxFilters = new ArrayList<>();

        URLClassLoader myClassLoader = new URLClassLoader( new URL[] { url } );

        for(Object v: classMap.values()) {

            try {
                // 应用发布部分
                Class classObj = myClassLoader.loadClass(v.toString());

                // 获取类标记的注解信息
                BoxApp boxAppAn = (BoxApp) classObj.getAnnotation(BoxApp.class);
                if (boxAppAn != null) {

                    // 封装项目信息
                    com.wzy.server.jar.api.config.BoxApp boxProjectVo = new com.wzy.server.jar.api.config.BoxApp();
                    boxProjectVo.setName(boxAppAn.name());
                    boxProjectVo.setRoute(boxAppAn.path());
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
                            boxApiVo.setRoute(boxApi.path());
                            boxApiVo.setRunClass(v.toString());
                            boxApiVo.setLinkUrl(boxProjectVo.getRoute());
                            boxApiVo.setRunFunction(method.getName());
                            boxApiVos.add(boxApiVo);
                       }
                   }
                } else {
                    // 过滤器发布部分
                    BoxWorkFilter workFilter = (BoxWorkFilter) classObj.getAnnotation(BoxWorkFilter.class);
                    if (workFilter != null) {
                        BoxFilter boxFilter = new BoxFilter();
                        boxFilter.setName(workFilter.name());
                        boxFilter.setPath(workFilter.path());
                        boxFilter.setClassName(classObj.getName());
                        boxFilters.add(boxFilter);
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
        scanJar.setBoxFilters(boxFilters);
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
     *
     */
    public static synchronized List<Jar> getJarmaps(){
        List<Jar> jars = new ArrayList<>();
        jarmaps.forEach((k,v) ->{
            jars.add(v);
        });
        return jars;
    }

    public static synchronized void remove(String md5, Integer appId){
        try {
            Jar jar = getJar(md5);
            if (jar == null) return;
            jar.getClassLoader().close();
            ClassLoaderUtil.releaseLoader(jar.getClassLoader());
            jarmaps.remove(md5);
            jarIdToMd5Map.remove(appId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized void removes(){
        jarIdToMd5Map.clear();
        jarmaps.forEach((k,v)->{
            try {
                v.getClassLoader().close();
                ClassLoaderUtil.releaseLoader(v.getClassLoader());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        jarmaps.clear();
    }
}
