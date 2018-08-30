package com.wzy.mapper.provide;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class BoxMoudulaProvide {

    /**
     * 分页查询
     * @param map
     * @return
     */
    public String getList(Map map) {
        Map keys = (Map) map.get("keys");
        String sql = "select " +
                "t.moudularId," +
                "t.projectId," +
                "t.moddularName," +
                "t.moddularRoute," +
                "t.jarName," +
                "t.jarUrl," +
                "t.jarVersion," +
                "t.jarMd5," +
                "t.isUpdate," +
                "t.createTime " +
                "from box_moudular t where 1 = 1";
                if (keys != null) {
                    if (keys.get("projectId") != null) {
                        sql += " and t.projectId="+keys.get("projectId");
                    }
                    if (keys.get("moubularName") != null) {
                        sql += " and t.moddularName like '%"+keys.get("moubularName")+"%'";
                    }
                }
                sql += " limit "+map.get("pageNo")+","+map.get("pageSize");
        return sql;
    }

    /**
     * 分页查询
     * @param map
     * @return
     */
    public String getCount(Map map) {
        Map keys = (Map) map.get("keys");
        String sql = "select count(*) " +
                "from box_moudular t where 1 = 1 ";
        if (keys != null) {
            if (keys.get("projectId") != null) {
                sql += " and t.projectId="+keys.get("projectId");
            }
            if (keys.get("moubularName") != null) {
                sql += " and t.moddularName like '%"+keys.get("moubularName")+"%'";
            }
        }
        return sql;
    }
}
