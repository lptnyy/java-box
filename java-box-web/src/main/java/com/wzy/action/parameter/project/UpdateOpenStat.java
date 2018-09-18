package com.wzy.action.parameter.project;

import com.wzy.util.annotation.check.IsNotLong;
import com.wzy.util.annotation.check.IsNotNull;

public class UpdateOpenStat {
    @IsNotNull(message = "状态不能为空")
    @IsNotLong(message = "状态只能是数字")
    Integer openStat;
    @IsNotNull(message = "状态不能为空")
    @IsNotLong(message = "状态只能是数字")
    Integer id;
    String jsonp;

    public String getJsonp() {
        return jsonp;
    }

    public void setJsonp(String jsonp) {
        this.jsonp = jsonp;
    }

    public Integer getOpenStat() {
        return openStat;
    }

    public void setOpenStat(Integer openStat) {
        this.openStat = openStat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
