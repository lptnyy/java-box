package com.wzy.action.parameter.project;

import com.wzy.util.annotation.check.IsNotLong;
import com.wzy.util.annotation.check.IsNotNull;

public class ListProject {
    String projectName;
    @IsNotNull(message = "pageNo不能为空")
    @IsNotLong(message = "pageNo只能是数字")
    Integer pageNo;
    @IsNotNull(message = "pageSize不能为空")
    @IsNotLong(message = "pageSize只能是数字")
    Integer pageSize;
    String jsonp;
    public String getJsonp() {
        return jsonp;
    }
    public void setJsonp(String jsonp) {
        this.jsonp = jsonp;
    }
    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public String getProjectName() {
        return projectName;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
