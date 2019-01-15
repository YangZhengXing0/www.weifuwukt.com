package com.weifuwukt.springboot.redis.ehcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringbootRedisEhcacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRedisEhcacheApplication.class, args);
    }

}

