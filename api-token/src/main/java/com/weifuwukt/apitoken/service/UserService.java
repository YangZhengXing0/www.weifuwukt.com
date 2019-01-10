package com.weifuwukt.apitoken.service;

import com.weifuwukt.apitoken.dao.UserDao;
import com.weifuwukt.apitoken.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author 杨郑兴
 * @Date 2019/1/10 18:11
 * @官网 www.weifuwukt.com
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User insert(User user){
        UUID uuid = UUID.randomUUID();
        user.setId(uuid.toString());
        User save = userDao.save(user);
        return user;
    }
}
