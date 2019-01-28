package com.wzy.server.jar.api.config;

import java.util.Date;

public class BoxAppApi {
    Integer apiId;
    Integer appId;
    String route;
    String name;
    String jarMd5;
    Integer stats;
    String linkUrl;
    String runClass;
    String runFunction;
    Date createTime;

    public Integer getApiId() {
        return apiId;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public void setApiId(Integer apiId) {
        this.apiId = apiId;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getRunClass() {
        return runClass;
    }

    public void setRunClass(String runClass) {
        this.runClass = runClass;
    }

    public String getRunFunction() {
        return runFunction;
    }

    public void setRunFunction(String runFunction) {
        this.runFunction = runFunction;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
