package AopTransaction.controller;

import AopTransaction.service.UserService;
import AopTransaction.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 杨郑兴
 * @Date 2018/12/16 17:00
 * @官网 www.weifuwukt.com
 */
@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;


    @RequestMapping("/add")
    @ResponseBody
    @Transactional
    public String add(){
            userServiceImpl.add("test1","password1");
//            int i = 1 / 0;
            userServiceImpl.add("test2","password2");
        return "ok";
    }
}
