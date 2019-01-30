package com.wzy.action.parameter.user;

import com.wzy.util.annotation.check.IsNotNull;

public class delConfig {
    @IsNotNull(message = "key不能为空")
    String key;
    @IsNotNull(message = "id不为空")
    Integer id;
    String jsonp;

    public String getKey() {
        return key;
    }

    public String getJsonp() {
        return jsonp;
    }

    public void setJsonp(String jsonp) {
        this.jsonp = jsonp;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
