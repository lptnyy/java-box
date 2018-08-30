package com.wzy.action.parameter.moudular;

import com.wzy.util.annotation.check.IsNotLong;
import com.wzy.util.annotation.check.IsNotNull;

public class IdMoudular {
    @IsNotNull(message = "id不能为空")
    @IsNotLong(message = "id只能是数字")
    Integer id;
    String jsonp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getJsonp() {
        return jsonp;
    }

    public void setJsonp(String jsonp) {
        this.jsonp = jsonp;
    }
}
