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

    @Update("update box_user set user_token=#{token} where user_id=#{user_id}")
    int updateToKen(@Param(value = "user_id") int id,@Param(value ="token") String token);

    @SelectProvider(type = BoxUserProvide.class, method = "get")
    BoxUser get(@Param(value = "keys") Map map);

    @SelectProvider(type = BoxUserProvide.class, method = "getList")
    List<BoxUser> getList(@Param(value = "keys") Map map);
}
