package com.weifuwukt.springboot.redis.ehcache.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 杨郑兴
 * @Date 2019/1/13 17:40
 * @官网 www.weifuwukt.com
 */
@Data
public class User implements Serializable {

    private String id;
    private String username;
    private String password;
}
