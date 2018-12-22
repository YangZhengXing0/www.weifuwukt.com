package springmvc.emded.tomcat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 杨郑兴
 * @Date 2018/12/22 19:09
 * @官网 www.weifuwukt.com
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/index",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String index(){
        return "ok!成功了！";
    }

    //idea这里视图到jsp解析不成功，eclipse就可以，还没找到解决方法
    @RequestMapping("/hello")
    public String hello(ModelAndView mv){
        mv.addObject("message","ok!成功了！");
        return "hello";
    }
}

