package transaction.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import transaction.pojo.User;
import transaction.service.UserService;

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
    @Autowired
    private User user;

    //增
    // http://localhost/insert?username="www.weifuwukt.com"&password="123"&n=1 数据库中能正常添加
    // http://localhost/insert?username="www.weifuwukt.com"&password="123"&n=0 不能添加，事务回滚了
    @RequestMapping("/insert")
    @ResponseBody
    @Transactional//这里不能try，否则事务aop获取不到异常，就不能回滚
    public String insert(User user,Integer n){
        int num = 1/n;
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
        log.info(user.toString());
        log.info(username);
        User user = userService.findByName(username);
        if (StringUtils.isEmpty(user)) {
            return "没查到数据";
        }
        return user.toString();
    }
}
