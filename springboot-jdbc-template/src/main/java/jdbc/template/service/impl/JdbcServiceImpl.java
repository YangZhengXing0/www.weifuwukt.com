package jdbc.template.service.impl;

import jdbc.template.pojo.User;
import jdbc.template.service.JdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 公司用jdbc特别少，一般用jpa,mybatis，所以不用太多的去学习，用的时候在去随便看下就会了
 * @author 杨郑兴
 * @Date 2018/12/21 19:36
 * @官网 www.weifuwukt.com
 */
@Service
public class JdbcServiceImpl implements JdbcService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> select() {
        String sql ="select * from user";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }
}
