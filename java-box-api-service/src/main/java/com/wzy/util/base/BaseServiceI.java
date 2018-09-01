package com.wzy.util.base;

import java.util.List;
import java.util.Map;

public interface BaseServiceI<T> {
    public int add(T t) throws Exception;
    public int del(int id) throws Exception;
    public int update(T t) throws Exception;
    public T get(int id) throws Exception;
    public List<T> getList(int pageNo, int pageSize) throws Exception;
    public List<T> getFindList(Map<String,Object> keys, int pageNo, int pageSize) throws Exception;
    public int getFindListCount(Map<String,Object> keys);
}
