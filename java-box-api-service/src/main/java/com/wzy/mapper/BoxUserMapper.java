package com.wzy.mapper;

import com.wzy.entity.BoxUser;
import com.wzy.mapper.provide.BoxUserProvide;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface BoxUserMapper {

    @Insert("insert into box_user(user_id,user_name,user_pass,user_stat,login_time,user_token,create_time) values (#{user_id},#{user_name},#{user_pass},#{user_stat},#{login_time},#{user_token},#{create_time})")
    int add(BoxUser user);

    @Delete("delete from box_user t  where t.user_id=#{user_id}")
    int del(@Param(value = "user_id") Integer id);

    @Select("select t.user_id,t.user_name,t.user_pass,t.user_stat,t.login_time,t.user_token,t.create_time from box_user t where t.user_id=#{user_id}")
    BoxUser get(@Param(value = "user_id") Integer id);

    @SelectProvider(type = BoxUserProvide.class, method = "getList")
    List<BoxUser> getList(Map map);
}
