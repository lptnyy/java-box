package com.wzy.util.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BaseMapperI<T> {
    public int add(T t);
    public int del(int id);
    public int update(T t);
    public List<T> getList(@Param("pageNo") int pageNo,@Param("pageSize") int pageSize,@Param("keys") Map map);
    public int getListCount(@Param("keys") Map map);
}
