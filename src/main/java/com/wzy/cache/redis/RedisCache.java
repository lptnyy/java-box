package com.wzy.cache.redis;
import com.wzy.cache.BoxCache;
import com.wzy.config.Config;
import redis.clients.jedis.Jedis;
import java.util.List;

public class RedisCache implements BoxCache{

    @Override
    public String get(String key) {
        Jedis jedis = null;
        String value = null;
        try {
            jedis = RedisConfiguraion.jedisPool().getResource();
            if (jedis.exists(key)) {
                value = jedis.get(key);
                Config.log.debug("getString {"+key+"} = {"+value+"}");
            }
        } catch (Exception e) {
            Config.log.error(e);
        } finally {
            jedis.close();
        }
        return value;
    }

    @Override
    public String set(String key, String value, int cacheSeconds) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisConfiguraion.jedisPool().getResource();
            if (cacheSeconds > 0) {
                result = jedis.setex(key,cacheSeconds,value);
            }else{
                result = jedis.set(key, value);
            }
            Config.log.debug("getString {"+key+"} = {"+value+"}");
        } catch (Exception e) {
            Config.log.error(e);
        } finally {
            jedis.close();
        }
        return result;
    }

    @Override
    public List<String> getList(String key) {
        List<String> value = null;
        Jedis jedis = null;
        try {
            jedis = RedisConfiguraion.jedisPool().getResource();
            if (jedis.exists(key)) {
                value = jedis.lrange(key, 0, -1);
                Config.log.debug("getString {"+key+"} = {"+value+"}");
            }
        } catch (Exception e) {
            Config.log.error(e);
        } finally {
            jedis.close();
        }
        return value;
    }
}
