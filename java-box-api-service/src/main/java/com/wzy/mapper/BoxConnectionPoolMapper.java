package com.wzy.mapper;

import com.wzy.entity.BoxConnectionPool;
import org.apache.ibatis.annotations.*;

import java.util.List;
public interface BoxConnectionPoolMapper {

    @Select("select t.id,t.name,t.className,t.methods,t.jarName,t.jarUrl,t.jarMd5,t.configStr,t.stat,t.createTime from box_connection_pool t")
    public List<BoxConnectionPool> getList();

    @Select("select t.id,t.name,t.className,t.methods,t.jarName,t.jarUrl,t.jarMd5,t.configStr,t.stat,t.createTime from box_connection_pool t where t.id in(#{id})")
    public List<BoxConnectionPool> getInIdList(@Param(value = "id") String id);

    @Delete("delete from box_connection_pool where id=#{id}")
    public int delete(@Param(value = "id") int id);

    @Insert("insert into box_connection_pool(name,className,methods,jarName,jarUrl,jarMd5,configStr) values (#{name},#{className},#{methods},#{jarName},#{jarUrl},#{jarMd5},#{configStr})")
    public int save(BoxConnectionPool connection);

    @Update("update box_connection_pool set stat=#{sts} where id=#{id}")
    public int updateStat(@Param(value = "id") int id,@Param(value = "sts") int sts);

}
