package com.wzy.action.parameter.user;

import com.wzy.util.annotation.check.IsNotNull;

public class GetAppApiList {
    @IsNotNull(message = "send ID is not null")
    Integer appId;
    String jsonp;

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getJsonp() {
        return jsonp;
    }

    public void setJsonp(String jsonp) {
        this.jsonp = jsonp;
    }
}
