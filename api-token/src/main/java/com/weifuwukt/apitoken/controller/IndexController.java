package com.weifuwukt.apitoken.controller;

import com.weifuwukt.apitoken.annotation.ApiToken;
import com.weifuwukt.apitoken.aop.ApiTokenAop;
import com.weifuwukt.apitoken.pojo.User;
import com.weifuwukt.apitoken.service.UserService;
import com.weifuwukt.apitoken.utils.RedisTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 杨郑兴
 * @Date 2019/1/10 3:36
 * @官网 www.weifuwukt.com
 */
@Controller
public class IndexController {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisTokenUtils redisTokenUtils;

    @RequestMapping("/")
    public String index(Model model){
        //每次访问否生成一个不同的Token
        String token = redisTokenUtils.getToken();
        model.addAttribute("token", token);
        return "index";
    }

    @PostMapping(value = "/insert")
    @ResponseBody
    @ApiToken(type= ApiTokenAop.FROM)
    public String insert (User user){
        User insert = userService.insert(user);
        if(StringUtils.isEmpty(insert)){
            return "插入失败";
        }
        return "插入成功！";
    }
    @PostMapping(value = "/insert1")
    @ResponseBody
    @ApiToken(type= ApiTokenAop.HEADER)
    public String insert1 (@RequestBody User user){
        User insert = userService.insert(user);
        if(StringUtils.isEmpty(insert)){
            return "插入失败";
        }
        return "插入成功！";
    }
}
