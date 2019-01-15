package com.weifuwukt.springboot.redis.ehcache.bean;

import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 杨郑兴
 * @Date 2019/1/15 19:13
 * @官网 www.weifuwukt.com
 */
@Configuration
public class MyEhcacheManager {

    @Bean
    public CacheManager ehcacheManager(){
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                //缓存别名
                .withCache("users", CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, Object.class,ResourcePoolsBuilder.heap(100)))
                .build();
        //创建之后立即初始化
        cacheManager.init();
        return cacheManager;
    }
}
