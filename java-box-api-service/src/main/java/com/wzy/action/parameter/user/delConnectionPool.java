package com.wzy.action.parameter.user;

import com.wzy.util.annotation.check.IsNotNull;

public class delConnectionPool {
    String jsonp;
    @IsNotNull(message = "id不能为空")
    Integer id;

    public String getJsonp() {
        return jsonp;
    }

    public void setJsonp(String jsonp) {
        this.jsonp = jsonp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
