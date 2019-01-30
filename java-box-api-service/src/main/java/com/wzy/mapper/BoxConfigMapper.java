package com.wzy.mapper;

import com.wzy.entity.BoxConfig;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BoxConfigMapper {

    @Select("select t.id,t.k,t.v,t.createTime from box_config t where t.k = #{key} limit 1")
    BoxConfig get(String key);

    @Insert("insert into box_config(k,v) values (#{k},#{v})")
    int add(BoxConfig boxConfig);

    @Delete("delete from box_config where id=#{id}")
    int del(int id);

    @Select("select t.id,t.k,t.v,t.createTime from box_config t order by t.id desc")
    List<BoxConfig> getList();

    @Select("select count(*) from box_config t")
    int getListCount();
}
