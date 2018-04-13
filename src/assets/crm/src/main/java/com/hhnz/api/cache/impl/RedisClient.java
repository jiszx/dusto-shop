package com.hhnz.api.cache.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author: chaoyang.ren
 * @date:2016年8月8日
 * @time:下午5:16:55
 * @email:chaoyang.ren@foxmail.com
 */
@Repository("redisClient")
public class RedisClient {
	private static final Log LOG = LogFactory.getLog(RedisClient.class);
	@Autowired
	private JedisPool jedisPool;

	public Jedis getRedisClient() {
		try {
			Jedis jedis = jedisPool.getResource();
			return jedis;
		} catch (Exception e) {
			LOG.error("getRedisClient error!", e);
			throw e;
		}
	}
    
    /*@Autowired
    private ShardedJedisPool shardedJedisPool;*/
    /*public ShardedJedis getShardedRedisClient() {
        try {
            ShardedJedis shardJedis = shardedJedisPool.getResource();
            return shardJedis;
        } catch (Exception e) {
        	LOG.error("getRedisClent error!", e);
        }
        return null;
    }*/
}
