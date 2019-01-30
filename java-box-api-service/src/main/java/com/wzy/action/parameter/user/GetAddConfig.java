package com.wzy.action.parameter.user;

public class GetAddConfig {
    String jsonp;
    int pageNo;
    int pageSize;

    public String getJsonp() {
        return jsonp;
    }

    public void setJsonp(String jsonp) {
        this.jsonp = jsonp;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
