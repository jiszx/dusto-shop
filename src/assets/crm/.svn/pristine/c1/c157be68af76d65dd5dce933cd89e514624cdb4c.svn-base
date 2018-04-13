package com.hhnz.api.cache.impl;

import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.Jedis;

import com.hhnz.api.cache.CacheService;
import com.hhnz.util.JsonUtil;

/**
 * @author: chaoyang.ren
 * @date:2016年8月8日
 * @time:下午5:27:21
 * @email:chaoyang.ren@foxmail.com
 */
@Repository("cacheService")
public class RedisClientTemplate implements CacheService{
	private static final Log LOG = LogFactory.getLog(RedisClientTemplate.class);
	
	@Autowired
	private RedisClient redisClient;
	
	public void disconnect() {
        Jedis jedis = redisClient.getRedisClient();
        jedis.disconnect();
    }

	@Override
	public void delete(String key) {
		Jedis jedis = redisClient.getRedisClient();
		if (jedis == null) {
			return;
		}
		try {
			jedis.del(key);
		} catch (Exception e) {
			LOG.error("删除jedis key时发生异常！", e);
			throw e;
		} finally {
        	jedis.close();
        }
	}

    @Override
    public void put(String key, Object value) {
        Jedis jedis = redisClient.getRedisClient();
        if (jedis == null) {
            return;
        }
        try {
            jedis.set(key, JsonUtil.toJSON(value));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw e;
        } finally {
        	jedis.close();
        }
    }
    
    @Override
    public void put(String key, Object value, int seconds ) {
    	Jedis jedis = redisClient.getRedisClient();
    	if (jedis == null) {
    		return;
    	}
    	try {
    		jedis.set(key, JsonUtil.toJSON(value));
    		jedis.expire(key, seconds);
    	} catch (Exception e) {
    		LOG.error(e.getMessage(), e);
    		throw e;
    	} finally {
    		jedis.close();
    	}
    }
    
    @Override
    public boolean putIfAbsent(String key, Object value) {
      if (key == null) {
        return false;
      }
      Jedis client = null;
      try {
        client = redisClient.getRedisClient();
        return client.setnx(key, JsonUtil.toJSON(value)).longValue() == 1L;
      } catch (RuntimeException e) {
        LOG.error(e.getMessage(), e);
      } finally {
        IOUtils.closeQuietly(client);
      }
      return false;
    }

    @Override
    public String get(String key) {
        String result = null;
        Jedis jedis = redisClient.getRedisClient();
        if (jedis == null) {
            return result;
        }
        try {
            result = jedis.get(key);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw e;
        } finally {
            jedis.close();
        }
        return JsonUtil.fromJSON(result, String.class);
    }

	@Override
	public <T> T get(String key, Class<T> clazz) {
		T result = null;
        Jedis jedis = redisClient.getRedisClient();
        if (jedis == null) {
            return result;
        }
        try {
        	String s = jedis.get(key);
            if(StringUtils.isNotBlank(s)){
            	result = JsonUtil.fromJSON(s, clazz);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw e;
        } finally {
            jedis.close();
        }
        return result;
	}

	@Override
	public String getAndExpire(String key) {
		int seconds = 1800;
		String result = null;
        Jedis jedis = redisClient.getRedisClient();
        if (jedis == null) {
            return result;
        }
        try {
        	result = jedis.get(key);
        	if(StringUtils.isNotBlank(result)){
        		jedis.expire(key, seconds);
        	}
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw e;
        } finally {
            jedis.close();
        }
        return JsonUtil.fromJSON(result, String.class);
	}

	@Override
	public <T> T getAndExpire(String key, Class<T> clazz) {
		int seconds = 1800;
		T result = null;
        Jedis jedis = redisClient.getRedisClient();
        if (jedis == null) {
            return result;
        }
        try {
            String s = jedis.get(key);
            if(StringUtils.isNotBlank(s)){
            	result = JsonUtil.fromJSON(s, clazz);
            	jedis.expire(key, seconds);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw e;
        } finally {
            jedis.close();
        }
        return result;
	}
	
	@Override
	public String getAndRemove(String key) {
		String result = null;
		Jedis jedis = redisClient.getRedisClient();
		if (jedis == null) {
			return result;
		}
		try {
			result = jedis.get(key);
			if(StringUtils.isNotBlank(result)){
				jedis.del(key);
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw e;
		} finally {
			jedis.close();
		}
		return JsonUtil.fromJSON(result, String.class);
	}
	
	@Override
	public <T> T getAndRemove(String key, Class<T> clazz) {
		T result = null;
		Jedis jedis = redisClient.getRedisClient();
		if (jedis == null) {
			return result;
		}
		try {
			String s = jedis.get(key);
			if(StringUtils.isNotBlank(s)){
				result = JsonUtil.fromJSON(s, clazz);
				jedis.del(key);
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw e;
		} finally {
			jedis.close();
		}
		return result;
	}

	@Override
	public Set<String> getKeys(String keyExp) {
		Jedis jedis = redisClient.getRedisClient();
		try {
			Set<String> keys = jedis.keys(keyExp);
			return keys;
		} catch (Exception e) {
			LOG.error("获取jedis所有满足条件的keys时发生异常!", e);
			throw e;
		} finally {
			jedis.close();
		}
	}

	@Override
	public Long decr(String string) {
		Jedis jedis = redisClient.getRedisClient();
		try {
			return jedis.decr(string);
		} catch (Exception e) {
			LOG.error("获取jedis自减数值时异常!", e);
			throw e;
		} finally {
			jedis.close();
		}
	}
}
