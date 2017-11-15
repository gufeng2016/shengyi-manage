package com.sz.common.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;

import com.sz.common.exception.AppException;


/**
 * @author : yunxing.li
 * @date   : 2017年6月29日 下午6:09:23
 * @version: v1.0
 */
//@Component
public class JedisClient {
	
	@Autowired
	private JedisFactory jedisFactory;
	
	public void pub(String channel, String message) throws AppException{
		Jedis jedis = null;
		try {
			jedis = jedisFactory.getJedisInstance();
			jedis.publish(channel, message);
		} catch (Exception e) {
			throw new AppException("100", "发布消息失败", e);
		} finally{
			jedisFactory.release(jedis);
		}
	}
	
	public boolean hsetnx(String key, String field, String value) throws AppException{
		Jedis jedis = null;
		try {
			jedis = jedisFactory.getJedisInstance();
			return jedis.hsetnx(key, field, value) > 0L;
		} catch (Exception e) {
			throw new AppException("100", "缓存失败", e);
		} finally{
			jedisFactory.release(jedis);
		}
	}
	
	public void hdel(String key, String field) throws AppException{
		Jedis jedis = null;
		try {
			jedis = jedisFactory.getJedisInstance();
			jedis.hdel(key, field);
		} catch (Exception e) {
			throw new AppException("100", "清除缓存失败", e);
		} finally{
			jedisFactory.release(jedis);
		}
	}
}
