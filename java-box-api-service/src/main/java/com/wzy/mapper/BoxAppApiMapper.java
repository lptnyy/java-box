package com.wzy.mapper;

import com.wzy.entity.BoxApp;
import com.wzy.entity.BoxAppApi;
import org.apache.ibatis.annotations.*;

public interface BoxAppApiMapper {

    @Insert("insert into box_app_api(appId,route,name,jarMd5,stats,runClass,runFunction,linkUrl) " +
            "values (#{appId},#{route},#{name},#{jarMd5},#{stats},#{runClass},#{runFunction},#{linkUrl})")
    @Options(useGeneratedKeys=true, keyProperty="apiId", keyColumn="apiId")
    public int add(BoxAppApi boxAppApi);

    @Update("update box_app_api set " +
            "appId = #{appId},route=#{route},name=#{name}," +
            "jarMd5=#{jarMd5},runClass=#{runClass}," +
            "runFunction=#{runFunction},linkUrl=#{linkUrl}" +
            " where apiId=#{apiId}")
    public int update(BoxAppApi boxAppApi);

    @Select("select t.apiId,t.appId,t.route,t.name,t.jarMd5,t.stats" +
            ",t.runClass,t.runFunction,t.createTime,t.linkUrl from box_app_api " +
            "t where t.route=#{route} and t.appId=#{appId}")
    public BoxAppApi getRouteAppApi(@Param(value = "route") String route,@Param(value = "appId") String appId);


}
