package com.wzy.action.parameter.zk;

import com.wzy.util.annotation.check.IsNotNull;

public class ServerApi {
    @IsNotNull(message = "注册服务IP端口不能为空")
    String regsionIp;
    String jsonp;

    public String getRegsionIp() {
        return regsionIp;
    }

    public String getJsonp() {
        return jsonp;
    }

    public void setJsonp(String jsonp) {
        this.jsonp = jsonp;
    }

    public void setRegsionIp(String regsionIp) {
        this.regsionIp = regsionIp;
    }
}
