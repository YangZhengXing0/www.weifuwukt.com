package mybatis.dao;

import mybatis.pojo.User;
import org.apache.ibatis.annotations.*;

/**
 * @author 杨郑兴
 * @Date 2018/12/20 21:41
 * @官网 www.weifuwukt.com
 */
@Mapper
public interface UserDao {

    //增
    @Insert("insert into user(username,password) values (#{username},#{password})")
    int insert(User user);

    //删
    @Delete("delete from user where id = #{id}")
    int delete(Integer id);

    //改
    @Update("update user set username=#{username},password=#{password} where id =#{id}")
    int update(User user);

    //查
    @Select("select * from user where username=#{username}")
    @Results({
            @Result(property = "id",column = "id",id = true),
            @Result(property = "username",column = "username" ),
            @Result(property = "password",column = "password" )
    })
    User findByName(String username);
}
