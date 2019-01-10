package com.wzy.server.jar.api.config;

public class BoxFilter {
    String name;
    String path;
    String className;
    public String getName() {
        return name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
