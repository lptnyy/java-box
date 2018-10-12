package com.wzy.mapper;

import com.wzy.entity.BoxApp;
import com.wzy.mapper.provide.BoxAppProvide;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface BoxAppMapper {

    @Insert("insert into box_app(name,route,jarUrl,jarName,jarMd5,stats) values (#{name},#{route},#{jarUrl},#{jarName},#{jarMd5},#{stats})")
    @Options(useGeneratedKeys=true, keyProperty="appId", keyColumn="appId")
    public int add(BoxApp boxApp);

    @Update("update box_app set " +
            "name=#{name}, route=#{route},jarUrl=#{jarUrl}" +
            ",jarName=#{jarName},jarMd5=#{jarMd5}" +
            " where appId=#{appId}")
    public int update(BoxApp boxApp);

    @Select("select t.appId,t.name,t.route,t.jarUrl,t.jarName,t.jarMd5,t.stats,t.createTime from box_app t where t.route=#{route}")
    public BoxApp getRouteApp(@Param(value = "route") String route);

    @Select("select t.appId,t.name,t.route,t.jarUrl,t.jarName,t.jarMd5,t.stats,t.createTime from box_app t where t.appId=#{appId}")
    public BoxApp getApp(@Param(value = "appId") Integer appId);

    @SelectProvider(type = BoxAppProvide.class, method = "getList")
    public List<BoxApp> getList(@Param(value = "keys") Map map);

    @SelectProvider(type = BoxAppProvide.class, method = "getCount")
    public int getListCount(@Param(value = "keys") Map maps);

    @Delete("delete from box_app where appId=#{appId}")
    public int deleteApp(Integer appId);

    @Update("update box_app set stats=#{stats} where appId=#{appId}")
    public int updateStats(@Param(value = "appId") Integer appId,@Param(value = "stats") Integer stats);
}
