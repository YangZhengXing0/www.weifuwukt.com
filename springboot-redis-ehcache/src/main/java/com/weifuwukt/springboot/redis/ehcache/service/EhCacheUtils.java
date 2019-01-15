
package com.weifuwukt.springboot.redis.ehcache.service;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


@Component
public class EhCacheUtils {

	 @Autowired
	 private CacheManager cacheManager;
	// cacheName 和 key 区别 就是 redis中的db库 组
	// 添加本地缓存 (相同的key 会直接覆盖)
	public void put(String cacheName, String key, Object value) {
		//ehcacje2.x的方式
		/*CacheManager cacheManager = ehCacheCacheManager.getCacheManager();
		Cache cache = cacheManager.getCache(cacheName);
		Element element = new Element(key, value);
		cache.put(element);
		*/
		//ehcache3.x的方式
		Cache<String, Object> cache = cacheManager.getCache(cacheName, String.class, Object.class);
		cache.put(key,value);
	}

	// 获取本地缓存
	public Object get(String cacheName, String key) {
		//ehcacje2.x的方式
		/*Cache cache = ehCacheCacheManager.getCacheManager().getCache(cacheName);
		Element element = cache.get(key);
		return element == null ? null : element.getObjectValue();*/

		//ehcache3.x的方式
		Cache<String, Object> cache = cacheManager.getCache(cacheName, String.class, Object.class);
		Object value = cache.get(key);
		if(StringUtils.isEmpty(value)){
			return null;
		}
		return value;
	}

	public void remove(String cacheName, String key) {
		//ehcacje2.x的方式
		/*Cache cache = ehCacheCacheManager.getCacheManager().getCache(cacheName);
		cache.remove(key);*/
		//ehcache3.x的方式
		Cache<String, Object> cache = cacheManager.getCache(cacheName, String.class, Object.class);
		cache.remove(key);
	}
}
