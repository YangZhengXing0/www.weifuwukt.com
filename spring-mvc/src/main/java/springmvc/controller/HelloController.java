package springmvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import springmvc.annotation.MyController;
import springmvc.annotation.MyRequestMapping;

/**
 * @author 杨郑兴
 * @Date 2018/12/18 17:00
 * @官网 www.weifuwukt.com
 */
@MyController
@MyRequestMapping("/hello")
public class HelloController {

    @MyRequestMapping("/index")
    public String index(){
        System.out.println("------手写spring mvc框架------");
        return "index";
    }
}
