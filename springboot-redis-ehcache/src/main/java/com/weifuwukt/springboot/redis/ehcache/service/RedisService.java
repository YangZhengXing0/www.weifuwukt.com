package com.weifuwukt.springboot.redis.ehcache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisService {

	@Autowired
	private RedisTemplate redisTemplate;

	public void setString(String key, Object object) {
		 //开启事务权限
		redisTemplate.setEnableTransactionSupport(true);
		try {
			// 开启事务 begin
			redisTemplate.multi();
			redisTemplate.opsForValue().set(key, object);
			// 提交事务
			redisTemplate.exec();
		} catch (Exception e) {
			//需要回滚事务
			redisTemplate.discard();
		}
	}
	public Object getString(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	//删除
	public void delete(String key){
		//开启事务权限
		redisTemplate.setEnableTransactionSupport(true);
		try {
			//开启事务 begin
			redisTemplate.multi();
			redisTemplate.delete(key);
			// 提交事务
			redisTemplate.exec();
		} catch (Exception e) {
			//需要回滚事务
			redisTemplate.discard();
		}
	}

}
