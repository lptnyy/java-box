package com.wzy.action.parameter.moudular;

import com.wzy.util.annotation.check.IsNotLong;
import com.wzy.util.annotation.check.IsNotNull;

public class GetMoudularLIst {
    Integer projectId;
    @IsNotNull(message = "pageNo不能为空")
    @IsNotLong(message = "pageNo只能是数字")
    Integer pageNo;
    @IsNotNull(message = "pageSize不能为空")
    @IsNotLong(message = "pageSize只能是数字")
    Integer pageSize;
    String moubularName;

    public String getMoubularName() {
        return moubularName;
    }

    public void setMoubularName(String moubularName) {
        this.moubularName = moubularName;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getJsonp() {
        return jsonp;
    }

    public void setJsonp(String jsonp) {
        this.jsonp = jsonp;
    }

    String jsonp;
}
