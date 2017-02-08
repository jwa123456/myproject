package com.netty.util.memery;

import com.netty.contents.Params;
import com.netty.util.Iutil.IredisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by yh on 17-2-7.
 */
public class RedisUtil implements IredisUtil {

    private static JedisPoolConfig config = new JedisPoolConfig();
    private static JedisPool jedisPool = null;

    private RedisUtil(){
        init();
    }
    private static RedisUtil redisUtil = new RedisUtil();
    public static  RedisUtil getRedisUtil(){
        return redisUtil;
    }
    public static void init(){
        config.setMaxIdle(1000);
        config.setMaxTotal(1000);
        config.setMaxWaitMillis(2000);
        jedisPool = new JedisPool(config, Params.SEVER_ADDRESS,Params.SEVER_PORT);
    }
    public void returnJedis(Jedis jedis){
        jedisPool.returnResourceObject(jedis);
    }

    public boolean exists(String key) {
        Jedis jedis = jedisPool.getResource();
        Boolean result = jedis.exists(key);
        returnJedis(jedis);
        return result;
    }
}
