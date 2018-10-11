package com.wzy.action.parameter.user;

import com.wzy.util.annotation.check.IsNotNull;
import com.wzy.util.annotation.set.SetStringMd5;

public class Login {
    @IsNotNull(message = "密码不能为空")
    @SetStringMd5
    String user_pass;
    @IsNotNull(message = "账号不能为空")
    String user_name;
    String jsonp;
    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getJsonp() {
        return jsonp;
    }

    public void setJsonp(String jsonp) {
        this.jsonp = jsonp;
    }

    public String getUser_pass() {
        return user_pass;
    }

    public void setUser_pass(String user_pass) {
        this.user_pass = user_pass;
    }
}
