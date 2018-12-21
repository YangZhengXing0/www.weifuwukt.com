package jdbc.template.service;

import jdbc.template.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * @author 杨郑兴
 * @Date 2018/12/21 19:35
 * @官网 www.weifuwukt.com
 */
public interface JdbcService {

    List<Map<String, Object>> select();
}
