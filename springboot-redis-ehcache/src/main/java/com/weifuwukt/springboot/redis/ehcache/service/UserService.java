package com.weifuwukt.springboot.redis.ehcache.service;

import com.weifuwukt.springboot.redis.ehcache.dao.UserDao;
import com.weifuwukt.springboot.redis.ehcache.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


/**
 * @author 杨郑兴
 * @Date 2019/1/13 17:46
 * @官网 www.weifuwukt.com
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisService redisService;
    @Autowired
    private EhCacheUtils ehCacheUtils;
    private final String cacheName = "users";

    public User findById(String id){
        //key
        String key =key(id);
        //1.reids一级缓存中查询
        User user = (User) redisService.getString(key);
        if(!StringUtils.isEmpty(user)){
            //一级缓存中,查询到数据
            return user;
        }
        //2.一级缓存中没有数据，在二级缓存中窜查询
        user = (User)ehCacheUtils.get("users", key);
        if(!StringUtils.isEmpty(user)){
            //二级缓存中,查询到数据,则先储存在一级缓存中，再返回
            redisService.setString(key,user);
            return user;
        }
        //3.二级缓存中没有值，则取数据库中查询
        user= userDao.findById(id);
        if(StringUtils.isEmpty(user)){
            throw new RuntimeException("数据库查询数据异常");
        }
        //查到的数据分别缓存到一级、二级缓存中,再返回数据
        redisService.setString(key,user);
        ehCacheUtils.put(cacheName,key,user);
        return user;
    }

    public String key(String id){
        //key 以当前的类名+方法名称+id +参数值FD
        return this.getClass().getName() + "-" + Thread.currentThread().getStackTrace()[1].getMethodName()
                + "-id:" + id;
    }

    //删除
    public void delete(String id) {
        //key
        String key = key(id);
        redisService.delete(key);
        //删除二级缓存
        ehCacheUtils.remove(cacheName,key);
        //删除数据库操作
        userDao.delete(id);
    }
}
