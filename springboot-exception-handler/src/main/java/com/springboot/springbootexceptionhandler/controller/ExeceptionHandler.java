package com.springboot.springbootexceptionhandler.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 杨郑兴
 * @Date 2018/12/20 13:39
 * @官网 www.weifuwukt.com
 */
@ControllerAdvice(basePackages = "com.springboot.springbootexceptionhandler.controller")
public class ExeceptionHandler {

    //注意：大公司全局一定要做得就是把异常写到日志中，如MongoDB,然后用脚本定期检测异常
    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public Map<String,String> error(){
        HashMap<String, String> map = new HashMap<>();
        map.put("code","500");
        map.put("message","controller出现异常，正在使用全局捕获异常");
        return map;
    }
}
