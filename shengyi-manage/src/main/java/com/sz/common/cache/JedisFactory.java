package com.sz.common.cache;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author : yunxing.li
 * @date   : 2017年6月29日 上午11:55:32
 * @version: v1.0
 */
public class JedisFactory {

	//jedis对象池
    private JedisPool jedisPool;

    public JedisFactory() {
        super();
    }

    public Jedis getJedisInstance() {
        return jedisPool.getResource();
    }

    /**
     * 配合使用getJedisInstance方法后将jedis对象释放回连接池中
     *
     * @param jedis 使用完毕的Jedis对象
     * @return true 释放成功；否则返回false
     */
    public boolean release(Jedis jedis) {
        if (jedisPool != null && jedis != null) {
            jedisPool.returnResource(jedis);
            return true;
        }
        return false;
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }
}
