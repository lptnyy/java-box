package com.wzy.action.parameter.user;

import com.wzy.util.annotation.check.IsNotNull;

public class UpdateAppStats {
    @IsNotNull(message = "appId is not null")
    Integer appId;
    @IsNotNull(message = "stats is not null")
    Integer stats;
    String jsonp;
    public Integer getAppId() {
        return appId;
    }

    public String getJsonp() {
        return jsonp;
    }

    public void setJsonp(String jsonp) {
        this.jsonp = jsonp;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Integer getStats() {
        return stats;
    }

    public void setStats(Integer stats) {
        this.stats = stats;
    }
}
