package com.wzy.com.wzy.includejar.introduce;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

// 动态载入JAR包
public class JarTools {
    protected static Method addURL = null;

    static {
        try {
           addURL = URLClassLoader.class.getDeclaredMethod("addURL", new Class[] {URL.class});
           addURL.setAccessible(true);
        }catch (Exception e){

        }
    }

    public static Object loadJar(String jarFile, String className) {
        try {
            File file = new File(jarFile);
            if (!file.exists()) {
                throw new RuntimeException(jarFile+"不存在");
            }
            addURL.invoke(ClassLoader.getSystemClassLoader(), new Object[] {file.toURI().toURL()});
            return Class.forName(className,false,ClassLoader.getSystemClassLoader()).newInstance();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Object loadJar = loadJar("C:\\Users\\Administrator\\Workspaces\\MyEclipse Professional\\JavaBox\\out\\artifacts\\JavaBox_jar\\JavaBox.jar","com.wzy.com.wzy.includejar.test.TestJar");
        System.out.println(loadJar.getClass().getName());
    }
}
