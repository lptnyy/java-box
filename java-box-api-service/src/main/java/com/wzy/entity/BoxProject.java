package com.wzy.entity;

import java.io.Serializable;
import java.util.Date;

public class BoxProject implements Serializable {
    Integer projectId;
    String projectName;
    String Route;
    Integer openStat;
    Date createTime;

    public Integer getOpenStat() {
        return openStat;
    }

    public void setOpenStat(Integer openStat) {
        this.openStat = openStat;
    }

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
