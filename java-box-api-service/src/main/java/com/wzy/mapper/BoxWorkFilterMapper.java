package com.wzy.mapper;

import com.wzy.entity.BoxWorkFilter;
import com.wzy.mapper.provide.BoxWorkFilterProvide;
import org.apache.ibatis.annotations.*;
import java.util.List;
import java.util.Map;

public interface BoxWorkFilterMapper {

    /**
     * 添加过滤器信息
     * @param workFilter
     * @return
     */
    @Insert("insert into box_work_filter(jarUrl,jarMd5,name,path,className) values (#{jarUrl},#{jarMd5},#{name},#{path},#{className})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int add(BoxWorkFilter workFilter);

    /**
     * 删除一条数据
      * @param id
     * @return
     */
    @Delete("delete from box_work_filter where id=#{id}")
    int del(@Param(value = "id") Integer id);

    /**
     * 查询单条数据
     * @param map
     * @return
     */
    @SelectProvider(type = BoxWorkFilterProvide.class, method = "get")
    BoxWorkFilter get(@Param("keys") Map map);

    /**
     * 修改数据
     * @param workFilter
     * @return
     */
    @Update("update box_work_filter set jarUrl=#{jarUrl},jarMd5=#{jarMd5},name=#{name},path=#{path},className=#{className} where id=#{id}")
    int update(BoxWorkFilter workFilter);

    /**
     * 查询所有列表
     * @param map
     * @return
     */
    @SelectProvider(type = BoxWorkFilterProvide.class, method = "getList")
    List<BoxWorkFilter> getList(@Param("keys") Map map);

    /**
     * 查询数据行数
     * @return
     */
    @SelectProvider(type = BoxWorkFilterProvide.class, method = "getListCount")
    int getListCount(@Param("keys") Map map);

    /**
     * 修改狀態
     * @param id
     * @param stat
     * @return
     */
    @Update("update box_work_filter set stat = #{stat} where id=#{id}")
    int updateStart(@Param(value ="id") int id, @Param(value = "stat") int stat);
}
