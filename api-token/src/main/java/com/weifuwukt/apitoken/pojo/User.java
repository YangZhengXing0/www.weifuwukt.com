package com.weifuwukt.apitoken.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 杨郑兴
 * @Date 2019/1/10 18:02
 * @官网 www.weifuwukt.com
 */
@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    private String id;
    private String username;
    private String password;
}
