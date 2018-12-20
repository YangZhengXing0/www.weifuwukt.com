package springmvc.annotation.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author 杨郑兴
 * @Date 2018/12/16 15:24
 * @官网 www.weifuwukt.com
 */
@Repository
public class UserDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public void add(String username, String password) {
        String sql = "INSERT INTO user(username,password) VALUES(?,?);";
        int updateResult = jdbcTemplate.update(sql, username, password);
        System.out.println("updateResult:" + updateResult);
    }

}
