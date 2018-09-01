package com.wzy.util.redis;
import com.wzy.util.SerializableUtil;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class RedisUtil<T> {
    boolean isCheck = false;
    String key;
    // 可以是任何对象的一种
    T t;
    SerializableUtil<T> serializableUtil = new SerializableUtil<T>();
    // redis 配置信息
    StringRedisTemplate stringRedisTemplate;

    IRedisCallback<T> redisCallback;

    public RedisUtil(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * radis存对象
     * @return
     */
    public RedisUtil<T> set(String key, T t) throws IOException {
        this.t = t;
        String returnStr = serializableUtil.getObjectSerializableSting(t);
        stringRedisTemplate.opsForValue().set(key, returnStr);
        return this;
    }

    /**
     * 在redis里获取对象
     * @param key
     * @return
     */
    public T get(String key) throws UnsupportedEncodingException {
        String returnstr = stringRedisTemplate.opsForValue().get(key);
        return serializableUtil.getStringSerializableObject(returnstr);
    }



    /**
     * 验证数据是否存在
     * @param key
     * @return
     * @throws UnsupportedEncodingException
     */
    public RedisUtil<T> check(String key) throws UnsupportedEncodingException {
        this.key = key;
        String returnstr = stringRedisTemplate.opsForValue().get(key);
        if (returnstr != null && !returnstr.equals("")) {
            t = serializableUtil.getStringSerializableObject(returnstr);
            isCheck = true;
        } else {
            t = redisCallback.callback();
            isCheck = false;
        }
        return this;
    }

    // 不覆盖
    public final static int NOT_COVER = 0;
    // 覆盖
    public final static int COVER = 1;

    /**
     * 同步数据到redis  0 存在的数据不覆盖 1 覆盖所有数据
     * @return
     */
    public RedisUtil<T> synchronization(int i) throws IOException {
        switch (i){
            case COVER:
                set(key, t);
                break;
            case NOT_COVER:
                if (isCheck == false){
                    set(key, t);
                }
                break;
        }
        return this;
    }

    /**
     * 执行业务函数接口
     * @param callback
     * @return
     */
    public RedisUtil<T> setCallback(IRedisCallback callback){
        this.redisCallback = callback;
        return this;
    }

    /**
     * 返回一个取出一个对象
     * @return
     */
    public T getObject(){
        return t;
    }
}
