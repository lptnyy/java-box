package com.wzy.mapper.provide;

import java.util.Map;

public class BoxApiProvide {

    /**
     * 分页查询
     * @param map
     * @return
     */
    public String getList(Map map) {
        String sql = "select " +
                "t.apiId," +
                "t.moudularId," +
                "t.apiName," +
                "t.apiRoute," +
                "t.classFuntion," +
                "t.sendStat," +
                "t.createTime " +
                "from box_moudularapi t ";
                if (map.get("keys") != null) {
                    Map keyMap = (Map) map.get("keys");
                    if (keyMap.get("moudularId") != null) {
                          sql += "where t.moudularId="+keyMap.get("moudularId");
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
        String sql = "select count(*) " +
                "from box_moudularapi t ";
            if (map.get("keys") != null) {
                Map keyMap = (Map) map.get("keys");
                if (keyMap.get("moudularId") != null) {
                    sql += "where t.moudularId="+keyMap.get("moudularId");
                }
            }
            return sql;
    }
}
