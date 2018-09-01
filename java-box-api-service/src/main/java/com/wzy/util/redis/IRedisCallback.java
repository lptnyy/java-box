package com.wzy.util.redis;

/**
 * 函数接口
 * @param <T>
 */
public interface IRedisCallback<T> {
    public T callback();
}
