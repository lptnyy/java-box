package com.wzy.cache;

import java.util.List;

public interface BoxCache {
    public String get(String key);
    public String set(String key,String value, int cacheSeconds);
    public List<String> getList(String key);
}
