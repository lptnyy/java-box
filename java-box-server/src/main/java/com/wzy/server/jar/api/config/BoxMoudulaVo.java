package com.wzy.server.jar.api.config;

public class BoxMoudulaVo {
    Integer moudularId;
    Integer projectId;
    String moddularName;
    String moddularRoute;
    String projectRoute;
    String jarName;
    String jarUrl;
    String jarVersion;
    Integer isUpdate;
    String jarMd5;

    public String getProjectRoute() {
        return projectRoute;
    }

    public void setProjectRoute(String projectRoute) {
        this.projectRoute = projectRoute;
    }

    public String getJarMd5() {
        return jarMd5;
    }

    public void setJarMd5(String jarMd5) {
        this.jarMd5 = jarMd5;
    }

    public Integer getMoudularId() {
        return moudularId;
    }

    public void setMoudularId(Integer moudularId) {
        this.moudularId = moudularId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getModdularName() {
        return moddularName;
    }

    public void setModdularName(String moddularName) {
        this.moddularName = moddularName;
    }

    public String getModdularRoute() {
        return moddularRoute;
    }

    public void setModdularRoute(String moddularRoute) {
        this.moddularRoute = moddularRoute;
    }

    public String getJarName() {
        return jarName;
    }

    public void setJarName(String jarName) {
        this.jarName = jarName;
    }

    public String getJarUrl() {
        return jarUrl;
    }

    public void setJarUrl(String jarUrl) {
        this.jarUrl = jarUrl;
    }

    public String getJarVersion() {
        return jarVersion;
    }

    public void setJarVersion(String jarVersion) {
        this.jarVersion = jarVersion;
    }

    public Integer getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Integer isUpdate) {
        this.isUpdate = isUpdate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    String createTime;
}
