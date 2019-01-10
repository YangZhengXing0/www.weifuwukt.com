package com.weifuwukt.apitoken.dao;

import com.weifuwukt.apitoken.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 杨郑兴
 * @Date 2019/1/10 18:10
 * @官网 www.weifuwukt.com
 */
public interface UserDao  extends JpaRepository<User,String> {
}
