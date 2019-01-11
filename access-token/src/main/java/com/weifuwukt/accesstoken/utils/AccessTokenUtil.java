package com.weifuwukt.accesstoken.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 杨郑兴
 * @Date 2019/1/11 18:21
 * @官网 www.weifuwukt.com
 */
@Component
public class AccessTokenUtil {

    @Autowired
    private IdWorker idWorker;

    public String getAccessToken(){
        return String.valueOf(idWorker.nextId());
    }
}
