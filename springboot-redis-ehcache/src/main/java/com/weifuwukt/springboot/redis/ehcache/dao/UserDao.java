package com.weifuwukt.springboot.redis.ehcache.dao;

import com.weifuwukt.springboot.redis.ehcache.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author 杨郑兴
 * @Date 2019/1/13 17:43
 * @官网 www.weifuwukt.com
 */
@Mapper
public interface UserDao {

    @Select("select * from user where id = #{id}")
    public User findById(String id);

    @Select("delete from user where id =#{id}")
    public void delete(String id);
}
