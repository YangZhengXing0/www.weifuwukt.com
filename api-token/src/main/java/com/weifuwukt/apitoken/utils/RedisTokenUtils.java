package com.weifuwukt.apitoken.utils;

import com.weifuwukt.apitoken.service.RedisApiTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class RedisTokenUtils {
	private long timeout = 60 * 60;
	@Autowired
	private RedisApiTokenService baseRedisService;
	@Autowired
	private IdWorker idWorker;

	// 将token存入在redis
	public String getToken() {
		String token = String.valueOf(idWorker.nextId());
		//redis中key和value都是同一个token值
		baseRedisService.setString(token, token, timeout);
		return token;
	}

	public boolean findToken(String tokenKey) {
		String token = (String) baseRedisService.getString(tokenKey);
		if (StringUtils.isEmpty(token)) {
			return false;
		}
		// token 获取成功后 删除对应tokenMapstoken
		baseRedisService.delKey(token);
		return true;
	}

}
