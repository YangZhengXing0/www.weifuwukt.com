package com.weifuwukt.ehcache.service;

import com.weifuwukt.ehcache.dao.UserDao;
import com.weifuwukt.ehcache.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


/**
 * @author 杨郑兴
 * @Date 2019/1/13 17:46
 * @官网 www.weifuwukt.com
 */
@Service
@CacheConfig(cacheNames = "users")
public class UserService {

    @Autowired
    private UserDao userDao;

    @Cacheable(cacheNames = "users", key = "#id")
    public User findById(String id){
        User user= userDao.findById(id);
        System.out.println(user.toString());
        return user;
    }

}
