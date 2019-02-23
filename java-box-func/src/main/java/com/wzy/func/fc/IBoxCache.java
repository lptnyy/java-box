package com.wzy.func.fc;

public interface IBoxCache {
    String get(String key);

    String set(String key,String value);

    String setEx( String key, String value, Integer second);

    String hget(String hkey, String key);

    void hset(String hkey,String key, String value);

    boolean hsetEx( String hkey,String key, String value,Integer second);

    long incr(String key, Integer num);

    boolean expire(String key,Integer second);

    void del(String key);

    long hdel(String hkey,String key);

    boolean exists(String key);

    Object[] keys(String key);
}
