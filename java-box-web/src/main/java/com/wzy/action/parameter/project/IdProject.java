package com.wzy.action.parameter.project;

import com.wzy.util.annotation.check.IsNotLong;
import com.wzy.util.annotation.check.IsNotNull;

public class IdProject {
    @IsNotNull(message = "Id不能为空")
    @IsNotLong(message = "id只能是数字类型")
    Integer id;
    String jsonp;
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
