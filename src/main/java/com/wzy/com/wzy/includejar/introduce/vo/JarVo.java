package com.wzy.com.wzy.includejar.introduce.vo;

import java.net.URLClassLoader;

public class JarVo {
    String httpUrl;
    String className;
    URLClassLoader classLoader;
    String jarDownUrl;
    Class objClass;

    public String getHttpUrl() {
        return httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }

    public Class getObjClass() {
        return objClass;
    }

    public void setObjClass(Class objClass) {
        this.objClass = objClass;
    }

    public String getJarDownUrl() {
        return jarDownUrl;
    }

    public void setJarDownUrl(String jarDownUrl) {
        this.jarDownUrl = jarDownUrl;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public URLClassLoader getClassLoader() {
        return classLoader;
    }

    public void setClassLoader(URLClassLoader classLoader) {
        this.classLoader = classLoader;
    }
}
