package com.wzy.entity;

import java.util.Date;

public class BoxApi {
    Integer apiId;
    Integer moudularId;
    String apiName;
    String apiRoute;
    String classFuntion;
    Integer sendStat;
    Date createTime;

    public Integer getApiId() {
        return apiId;
    }

    public void setApiId(Integer apiId) {
        this.apiId = apiId;
    }

    public Integer getMoudularId() {
        return moudularId;
    }

    public void setMoudularId(Integer moudularId) {
        this.moudularId = moudularId;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getApiRoute() {
        return apiRoute;
    }

    public void setApiRoute(String apiRoute) {
        this.apiRoute = apiRoute;
    }

    public String getClassFuntion() {
        return classFuntion;
    }

    public void setClassFuntion(String classFuntion) {
        this.classFuntion = classFuntion;
    }

    public Integer getSendStat() {
        return sendStat;
    }

    public void setSendStat(Integer sendStat) {
        this.sendStat = sendStat;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
