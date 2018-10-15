package com.wzy.entity;

import java.util.Date;

public class BoxApp {
    Integer appId;
    String name;
    String route;
    String jarUrl;
    String jarName;
    String jarMd5;
    Integer jarType;
    Integer stats;

    public Integer getAppId() {
        return appId;
    }

    public Integer getJarType() {
        return jarType;
    }

    public void setJarType(Integer jarType) {
        this.jarType = jarType;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getJarUrl() {
        return jarUrl;
    }

    public void setJarUrl(String jarUrl) {
        this.jarUrl = jarUrl;
    }

    public String getJarName() {
        return jarName;
    }

    public void setJarName(String jarName) {
        this.jarName = jarName;
    }

    public String getJarMd5() {
        return jarMd5;
    }

    public void setJarMd5(String jarMd5) {
        this.jarMd5 = jarMd5;
    }

    public Integer getStats() {
        return stats;
    }

    public void setStats(Integer stats) {
        this.stats = stats;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    Date createTime;
}
