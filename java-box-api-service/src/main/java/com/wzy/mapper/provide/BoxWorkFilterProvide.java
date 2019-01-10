package com.wzy.mapper.provide;

import java.util.Map;

public class BoxWorkFilterProvide {

    // 查询sql
    String selectSql = "select t.id,t.jarUrl,t.jarMd5,t.name,t.path,t.className,t.stat,t.createTime from box_work_filter t ";
    String getSelectCountSql = "select count(*) from box_work_filter t ";

    public String getList(Map map) {
        Map<String, Object> keys = (Map<String, Object>) map.get("keys");
        StringBuffer sql = new StringBuffer();
        sql.append(selectSql);
        sql.append(" where 1=1 ");
        for (String k : keys.keySet()) {
            if (!k.equals("pageNo")
                    && !k.equals("pageSize")
                    && !k.equals("class")
                    && keys.get(k) != null
                    && !keys.get(k).equals("")
                    && !k.equals("jsonp"))
                sql.append(" and t." + k + "='" + keys.get(k) + "'");
            else if (k.equals("pageNo")) {
                sql.append(" limit " + keys.get(k) + "," + keys.get("pageSize"));
            }
        }
        return sql.toString();
    }

    public String getListCount(Map map){
        Map<String, String> keys = (Map<String, String>) map.get("keys");
        StringBuffer sql = new StringBuffer();
        sql.append(getSelectCountSql);
        sql.append(" where 1=1 ");
        for (String k : keys.keySet()) {
            if (!k.equals("pageNo")
                    && !k.equals("pageSize")
                    && !k.equals("class")
                    && keys.get(k) != null
                    && !keys.get(k).equals("")
                    && !k.equals("jsonp"))
                sql.append(" and t." + k + "='" + keys.get(k) + "'");
        }
        return sql.toString();
    }

    public String get(Map map) {
        Map<String, String> keys = (Map<String, String>) map.get("keys");
        StringBuffer sql = new StringBuffer();
        sql.append(selectSql);
        sql.append(" where 1=1 ");
        keys.forEach((k,v) ->{
            sql.append(" and t."+k+"='"+v+"'");
        });
        return sql.toString();
    }
}
