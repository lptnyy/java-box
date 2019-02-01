package com.wzy.server.jar.loader.config;

import java.lang.reflect.Method;
import java.net.URLClassLoader;

public class Jar {
    String httpUrl;
    String className;
    String runFuntion;
    URLClassLoader classLoader;
    String jarDownUrl;
    Class objClass;
    String jarVersion;
    String jarMd5;
    Integer baseId;
    Object initObject;
    Method method;

    public Method getMethod() {
        return method;
    }
    public void setMethod(Method method) {
        this.method = method;
    }
    public String getJarMd5() {
        return jarMd5;
    }
    public void setJarMd5(String jarMd5) {
        this.jarMd5 = jarMd5;
    }
    public String getHttpUrl() {
        return httpUrl;
    }
    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }
    public Object getInitObject() {
        return initObject;
    }
    public void setInitObject(Object initObject) {
        this.initObject = initObject;
    }
    public String getRunFuntion() {
        return runFuntion;
    }
    public void setRunFuntion(String runFuntion) {
        this.runFuntion = runFuntion;
    }
    public Integer getBaseId() {
        return baseId;
    }
    public void setBaseId(Integer baseId) {
        this.baseId = baseId;
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
    public String getJarVersion() {
        return jarVersion;
    }
    public void setJarVersion(String jarVersion) {
        this.jarVersion = jarVersion;
    }
}
