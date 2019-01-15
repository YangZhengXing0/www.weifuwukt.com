package com.weifuwukt.springboot.redis.ehcache.controller;

import com.weifuwukt.springboot.redis.ehcache.pojo.User;
import com.weifuwukt.springboot.redis.ehcache.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 杨郑兴
 * @Date 2019/1/13 17:41
 * @官网 www.weifuwukt.com
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/{id}")
    public String findById(@PathVariable("id") String id){
        User user = userService.findById(id);
        return user.toString();
    }
    @RequestMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") String id){
        userService.delete(id);
        return "删除成";
    }
}
