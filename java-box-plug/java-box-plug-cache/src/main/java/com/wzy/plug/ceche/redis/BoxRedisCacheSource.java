package com.wzy.plug.ceche.redis;

import com.wzy.func.annotation.BoxBean;
import com.wzy.func.annotation.BoxBeanType;
import com.wzy.func.annotation.BoxConfigAdds;
import com.wzy.func.fc.IBoxCache;
import com.wzy.func.fc.IBoxClose;
import com.wzy.func.fc.IBoxInit;
import com.wzy.func.fc.IConfig;
import com.wzy.log.BoxLog;
import com.wzy.log.ILog;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@BoxBean(type = BoxBeanType.SINGLE_OBJECT)
@BoxConfigAdds(configs = {
        "redis_timeout=3000",
        "redis_passWord=",
        "redis_maxWait=1500",
        "redis_maxActive=100",
        "redis_testOnReturn=true",
        "redis_maxIdle=50",
        "redis_testOnBorrow=true",
        "redis_ip=192.168.30.199",
        "redis_port=6379"
})
public class BoxRedisCacheSource implements IBoxCache, IBoxInit, IBoxClose {
    ILog log = BoxLog.getInstance();

    @Override
    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.get(key);
        } catch (Exception e){
            log.error(e);
        } finally {
            returnResource(jedis);
        }
        return null;
    }

    @Override
    public String set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.set(key,value);
        } catch (Exception e){
            log.error(e);
        } finally {
            returnResource(jedis);
        }
        return null;
    }

    @Override
    public String setEx(String key, String value, Integer second) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.setex(key,second,value);
        } catch (Exception e){
            log.error(e);
        } finally {
            returnResource(jedis);
        }
        return null;
    }

    @Override
    public String hget(String hkey, String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.hget(hkey,key);
        } catch (Exception e){
            log.error(e);
        } finally {
            returnResource(jedis);
        }
        return null;
    }

    @Override
    public void hset(String hkey, String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.hset(hkey,key,value);
        } catch (Exception e){
            log.error(e);
        } finally {
            returnResource(jedis);
        }
    }

    @Override
    public boolean hsetEx(String hkey, String key, String value, Integer second) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.hset(hkey,key,value);
            jedis.expire(hkey, second);
            return true;
        } catch (Exception e){
            log.error(e);
        } finally {
            returnResource(jedis);
        }
        return false;
    }

    @Override
    public long incr(String key, Integer num) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.incrBy(key,num);
        } catch (Exception e){
            log.error(e);
        } finally {
            returnResource(jedis);
        }
        return 0;
    }

    @Override
    public boolean expire(String key, Integer second) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.expire(key,second);
            return true;
        } catch (Exception e){
            log.error(e);
        } finally {
            returnResource(jedis);
        }
        return false;
    }

    @Override
    public void del(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.del(key);
        } catch (Exception e){
            log.error(e);
        } finally {
            returnResource(jedis);
        }
    }

    @Override
    public long hdel(String hkey, String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.hdel(hkey,key);
        } catch (Exception e){
            log.error(e);
        } finally {
            returnResource(jedis);
        }
        return 0;
    }

    @Override
    public boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.exists(key);
        } catch (Exception e){
            log.error(e);
        } finally {
            returnResource(jedis);
        }
        return false;
    }

    @Override
    public Object[] keys(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.keys(key).toArray();
        } catch (Exception e){
            log.error(e);
        } finally {
            returnResource(jedis);
        }
        return new Object[0];
    }

    @Override
    public void close() throws Exception {
        jedisPool.close();
    }

    private JedisPool jedisPool = null;

    @Override
    public void init(IConfig config) throws Exception {
        int redis_timeout= Integer.valueOf(config.getValue("redis_timeout"));
        String redis_passWord= config.getValue("redis_passWord");
        int redis_maxWait= Integer.valueOf(config.getValue("redis_maxWait"));
        int redis_maxActive= Integer.valueOf(config.getValue("redis_maxActive"));
        Boolean redis_testOnReturn= Boolean.valueOf(config.getValue("redis_testOnReturn"));
        int redis_maxIdle = Integer.valueOf(config.getValue("redis_maxIdle"));
        Boolean redis_testOnBorrow= Boolean.valueOf(config.getValue("redis_testOnBorrow"));
        String redis_ip = config.getValue("redis_ip");
        int redis_port = Integer.valueOf(config.getValue("redis_port"));
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 设置最大连接数
        jedisPoolConfig.setMaxTotal(redis_maxActive); // 可以创建3000jedis实例 
        // 设置最大空闲连接数
        jedisPoolConfig.setMaxIdle(redis_maxIdle);
        //等待可用连接的最大时间
        jedisPoolConfig.setMaxWaitMillis(redis_maxWait);
        //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的
        jedisPoolConfig.setTestOnBorrow(redis_testOnBorrow);
        jedisPoolConfig.setTestOnReturn(redis_testOnReturn);
        if (redis_passWord == null || redis_passWord.equals(""))
            jedisPool = new JedisPool(jedisPoolConfig,redis_ip,redis_port,redis_timeout);
        else {
            jedisPool = new JedisPool(jedisPoolConfig,redis_ip,redis_port,redis_timeout,redis_passWord);
        }
        log.info("redis成功");
    }

    /**
     * 获取Jedis实例
     * 每次用完要将连接返回给连接池 jedis.close();
     */
    public Jedis getJedis(){
        if(jedisPool != null){
            Jedis resource = jedisPool.getResource();
            return resource;
        }else{
            return null;
        }
    }

    /** 
      * 回收Jedis对象资源 
      *     - 用户redis都要使用该方法释放资源, 否则一直占有实例资源
      * 
      * @param jedis 
      */
     public synchronized void returnResource(Jedis jedis){
        if(jedis != null){
            jedisPool.returnResource(jedis);
        }
     }

    @Override
    public void init() throws Exception {

    }
}
