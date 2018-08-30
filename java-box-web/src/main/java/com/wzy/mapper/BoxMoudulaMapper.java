package com.wzy.mapper;

import com.wzy.entity.BoxMoudula;
import com.wzy.mapper.provide.BoxMoudulaProvide;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

public interface BoxMoudulaMapper {

    /**
     * 查询列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @SelectProvider(type = BoxMoudulaProvide.class, method = "getList")
    public List<BoxMoudula> getlist(@Param("keys") Map keys, @Param("pageNo") int pageNo, @Param("pageSize") int pageSize);

    /**
     * 查询数据条数
     * @param keys
     * @return
     */
    @SelectProvider(type = BoxMoudulaProvide.class, method = "getCount")
    public int getListCount(@Param("keys") Map keys);

    /**
     * 添加数据
     * @param boxMoudula
     * @return
     */
    @Insert("insert into box_moudular(projectId,moddularName,moddularRoute,jarName,jarUrl,jarVersion,jarMd5) " +
            "values (#{projectId},#{moddularName},#{moddularRoute},#{jarName},#{jarUrl},#{jarVersion},#{jarMd5})")
    public int add(BoxMoudula boxMoudula);

    /**
     * 珊瑚一条数据
     * @param id
     * @return
     */
    @Delete("delete from box_moudular where moudularId = #{id}")
    public int del(@Param("id") int id);
}
