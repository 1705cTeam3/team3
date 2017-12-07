package com.jk.shiro;

import java.io.Serializable;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.jk.util.Const;

/** 
* @author 作者 尚峰: 
* @version 创建时间：2017年11月14日 上午10:06:00 
* 类说明 
*/
public class RedisCacheManager implements CacheManager, Serializable {

	  private transient static Logger log = LoggerFactory.getLogger(RedisCacheManager.class);
	  private transient RedisTemplate<Object, Object> redisTemplate;
	    
	  public RedisCacheManager() {
	    }
	    public RedisCacheManager(RedisTemplate<Object, Object> redisTemplate) {
	        this.redisTemplate = redisTemplate;
	    }
	@Override
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        if (!StringUtils.hasText(name)) {
            throw new IllegalArgumentException("Cache name cannot be null or empty.");
        }
        log.debug("redis cache manager get cache name is :{}", name);
        Cache cache = (Cache) redisTemplate.opsForValue().get(name);
        if (cache == null) {
            cache = new RedisCache<>(redisTemplate);
            redisTemplate.opsForValue().set(Const.shiro_cache_prefix + name, cache);
        }
        return cache;
	}

	public void setRedisTemplate(RedisTemplate<Object, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

}
