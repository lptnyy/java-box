package com.wzy.mapper;

import com.wzy.entity.BoxApi;
import com.wzy.mapper.provide.BoxApiProvide;
import com.wzy.util.base.BaseMapperI;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

public interface BoxApiMapper extends BaseMapperI<BoxApi> {

    @Override
    @Insert("insert into box_moudularapi(apiId,moudularId,apiName,apiRoute,classFuntion) " +
            "values (#{apiId},#{moudularId},#{apiName},#{apiRoute},#{classFuntion})")
    int add(BoxApi boxApi);

    @Override
    @Delete("delete from box_moudularapi  where apiId=#{id}")
    int del(int id);

    @Override
    int update(BoxApi boxApi);

    @Override
    @SelectProvider(type = BoxApiProvide.class, method = "getList")
    List<BoxApi> getList(@Param("pageNo") int pageNo,@Param("pageSize") int pageSize,@Param("keys") Map map);

    @Override
    @SelectProvider(type = BoxApiProvide.class, method = "getCount")
    int getListCount(@Param("keys") Map map);
}
