package com.wzy.action.parameter.user;

import com.wzy.util.annotation.check.IsNotNull;

public class AddConfig {
    @IsNotNull(message = "key不能为空")
    String key;
    @IsNotNull(message = "value不为空")
    String value;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
