package mybatis.controller;

import lombok.extern.slf4j.Slf4j;
import mybatis.pojo.User;

import mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 杨郑兴
 * @Date 2018/12/20 21:36
 * @官网 www.weifuwukt.com
 */
@Controller
@Slf4j
public class IndexController {
    @Autowired
    private UserService userService;

    //增 http://localhost/insert?username="www.weifuwukt.com"&password="123"
    @RequestMapping("/insert")
    @ResponseBody
    public String insert(User user){
        log.info(user.toString());
        int i = userService.insert(user);
        return i+"--"+user;
    }

    //删 http://localhost/delete?id=40
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(Integer id){
        log.info(id.toString());
        int i = userService.delete(id);
        return i+"--"+id;
    }

    //改 http://localhost/update?id=40&username="www.weifuwukt.com"&password="456"
    @RequestMapping("/update")
    @ResponseBody
    public String update(User user){
        log.info(user.toString());
        int i = userService.update(user);
        return i+"--"+user;
    }

    //查 http://localhost/select?username="www.weifuwukt.com"
    @RequestMapping("/select")
    @ResponseBody
    public String select(String username){
        log.info(username);
        User user = userService.findByName(username);
        if (StringUtils.isEmpty(user)) {
            return "没查到数据";
        }
        return user.toString();
    }
}
