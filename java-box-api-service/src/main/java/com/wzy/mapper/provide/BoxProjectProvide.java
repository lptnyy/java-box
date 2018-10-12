package com.wzy.mapper.provide;

import java.util.Map;

public class BoxProjectProvide {

    /**
     * 分页查询数据
     * @param
     * @param
     * @return
     */
    public String getList(Map map){
        Map keys = (Map) map.get("keys");
        String sql = "select " +
                "t.projectId," +
                "t.projectName," +
                "t.Route," +
                "t.openStat," +
                "t.createTime " +
                "from box_project t ";
                if (keys != null && keys.get("projectName") != null
                        && !keys.get("projectName").equals("")){
                    sql += " where t.projectName like '%"+keys.get("projectName")+"%'";
                }
                sql +=" order by t.projectId desc " +
                "limit "+map.get("pageNo")+","+map.get("pageSize");
        return sql;
    }

    /**
     * 根据ID字符串查询更多的
     * @param map
     * @return
     */
    public String getInIds(Map map){
        String sql = "select " +
                "t.projectId," +
                "t.projectName," +
                "t.Route," +
                "t.openStat," +
                "t.createTime " +
                "from box_project t order by t.projectId desc ";
        return sql;
    }

    /**
     * 根据ID字符串查询更多的
     * @param map
     * @return
     */
    public String getConut(Map map){
        Map keys = (Map) map.get("keys");
        String sql = "select " +
                "count(*) " +
                "from box_project t ";
        if (keys != null && keys.get("projectName") != null
                && !keys.get("projectName").equals("")){
            sql += " where t.projectName like '%"+keys.get("projectName")+"%'";
        }
        return sql;
    }
}
