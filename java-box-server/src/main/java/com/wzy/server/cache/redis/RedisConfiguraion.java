package com.wzy.server.cache.redis;
import com.wzy.util.properties.PropertiesUtil;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisConfiguraion {
    public static final int maxTotal = Integer.parseInt(PropertiesUtil.getConfigKey("maxTotal"));
    public static final int maxIdle = Integer.parseInt(PropertiesUtil.getConfigKey("maxIdle"));
    public static final int maxWaitMillis = Integer.parseInt(PropertiesUtil.getConfigKey("maxWaitMillis"));
    public static final boolean testOnBorrow = Boolean.parseBoolean(PropertiesUtil.getConfigKey("testOnBorrow"));
    public static final String CACHE_SERVER_IP = PropertiesUtil.getConfigKey("CACHE_SERVER_IP");;
    public static final int CACHE_SERVER_PORT = Integer.parseInt(PropertiesUtil.getConfigKey("CACHE_SERVER_PORT"));
    public static JedisPool jedisPool;

    /**
     * 获取redis 链接对象
     * @return
     */
    public static JedisPool jedisPool(){
        if (jedisPool == null) {
            jedisPool = new JedisPool(jedisPoolConfig(),CACHE_SERVER_IP,CACHE_SERVER_PORT);
        }
        return jedisPool;
    }

    public static JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        config.setTestOnBorrow(testOnBorrow);
        return config;
    }
}
