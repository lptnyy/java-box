package com.wzy.server.jar.api.config;

public class BoxProjectVo {
    Integer projectId;
    String projectName;
    String Route;
    Integer openStat;
    String createTime;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getRoute() {
        return Route;
    }

    public void setRoute(String route) {
        Route = route;
    }

    public Integer getOpenStat() {
        return openStat;
    }

    public void setOpenStat(Integer openStat) {
        this.openStat = openStat;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
