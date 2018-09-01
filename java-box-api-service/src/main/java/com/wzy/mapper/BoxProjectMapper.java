package com.wzy.mapper;

import com.wzy.entity.BoxProject;
import com.wzy.mapper.provide.BoxProjectProvide;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface BoxProjectMapper {

    /**
     * 查询一条数据
     * @param id
     * @return
     */

    @Select("select t.projectId,t.projectName,t.Route,t.openStat,t.createTime from box_project t where t.projectId=#{id}")
    public BoxProject get(int id);

    /**
     * 删除一条数据
     * @param id
     * @return
     */
    @Delete("delete from box_project where projectId=#{id}")
    public int del(int id);

    /**
     * 保存一条数据
     * @param boxProject
     * @return
     */
    @Insert("insert into box_project(projectId,projectName,Route,openStat,createTime) values (#{projectId},#{projectName},#{Route},#{openStat},#{createTime})")
    public int save(BoxProject boxProject);

    /**
     * 修改状态
     * @param id
     * @param openStat
     * @return
     */
    @Update("update box_project set openStat=#{openStat} where projectId=#{id}")
    public int updateOpenStat(@Param("id") int id,@Param("openStat") int openStat);

    /**
     * 分页查询数据
     * @param pageNo
     * @param pageSize
     * @return
     */
    @SelectProvider(type = BoxProjectProvide.class, method = "getList")
    public List<BoxProject> getList(@Param("keys") Map map, @Param(value = "pageNo") int pageNo, @Param(value = "pageSize") int pageSize);

    /**
     * 获取所有数据行数
     * @return
     */
    @SelectProvider(type = BoxProjectProvide.class, method = "getConut")
    public int getListCount(@Param("keys") Map map);
}
