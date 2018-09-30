package com.wzy.mapper.provide;

import java.util.Map;

public class BoxAppProvide {

    // 查询sql
    String selectSql = "select t.appId,t.name,t.route,t.jarUrl,t.jarName,t.jarMd5,t.stats,t.createTime from box_app t";
    String selectsqlCount = "select count(*) from box_app t";

    public String getList(Map map){
        StringBuffer sql = new StringBuffer();
        sql.append(selectSql);
        sql.append(" where 1=1 ");
        Map<String,String> keys = (Map<String, String>) map.get("keys");
        keys.forEach((k,v) ->{
            if(k.equals("pageNo")) {

            } else if (k.equals("pageSize")) {

            } else {
                if (!v.equals("") && v != null && v.indexOf("null") == -1)
                sql.append(" and t.").append(k).append(" ").append(v).append(" ");
            }
        });
        String pageNoStr = keys.get("pageNo");
        if (pageNoStr != null && !pageNoStr.equals("")) {
            sql.append("limit ").append(pageNoStr).append(",").append(keys.get("pageSize"));
        }
        return sql.toString();
    }

    public String getCount(Map map){
        StringBuffer sql = new StringBuffer();
        sql.append(selectsqlCount);
        sql.append(" where 1=1 ");
        Map<String,String> keys = (Map<String, String>) map.get("keys");
        keys.forEach((k,v) ->{
            if(k.equals("pageNo")) {

            } else if (k.equals("pageSize")) {

            } else {
                if (!v.equals("") && v != null && v.indexOf("null") == -1)
                sql.append(" and t.").append(k).append(" ").append(v).append(" ");
            }
        });
        return sql.toString();
    }
}
