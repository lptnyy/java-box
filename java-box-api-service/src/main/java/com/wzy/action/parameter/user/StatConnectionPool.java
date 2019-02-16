package com.wzy.action.parameter.user;

import com.wzy.util.annotation.check.IsNotNull;

public class StatConnectionPool {
    String jsonp;
    @IsNotNull(message = "id不能为空")
    Integer id;
    @IsNotNull(message = "stat不能为空")
    Integer stat;

    public String getJsonp() {
        return jsonp;
    }

    public Integer getStat() {
        return stat;
    }

    public void setStat(Integer stat) {
        this.stat = stat;
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
