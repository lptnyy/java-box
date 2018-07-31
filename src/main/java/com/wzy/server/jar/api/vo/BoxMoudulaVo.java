package com.wzy.server.jar.api.vo;

public class BoxMoudulaVo {
    Integer moudularId;
    Integer projectId;
    String moddularName;
    String moddularRoute;
    String jarName;
    String jarUrl;
    String jarVersion;
    Integer isUpdate;

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
