package jdbc.template.controller;

import jdbc.template.pojo.User;
import jdbc.template.service.impl.JdbcServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author 杨郑兴
 * @Date 2018/12/21 19:34
 * @官网 www.weifuwukt.com
 */
@RestController
public class IndexController {

    @Autowired
    private JdbcServiceImpl jdbcServiceImpl;

    //访问
    @RequestMapping("/jdbc")
    public List<Map<String, Object>> jdbc(String username){
        List<Map<String, Object>> list = jdbcServiceImpl.select();
        return list;
    }
}
