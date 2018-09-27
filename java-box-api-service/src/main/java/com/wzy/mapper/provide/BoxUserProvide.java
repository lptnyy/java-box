package com.wzy.mapper.provide;

import java.util.Map;

public class BoxUserProvide {
    // 查询sql
    String selectSql = "select t.user_id,t.user_name,t.user_pass,t.user_stat,t.login_time,t.user_token,t.create_time from box_user t ";

    public String getList(Map map){
        return "";
    }

    public String get(Map map) {
        return "";
    }
}
