package com.springboot.springbootexceptionhandler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 杨郑兴
 * @Date 2018/12/20 13:35
 * @官网 www.weifuwukt.com
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    @ResponseBody
    public Integer index(int i){
            int num = 1/i;
        return num;
    }
}
