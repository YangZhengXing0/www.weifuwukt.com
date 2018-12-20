package environment.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 杨郑兴
 * @Date 2018/12/20 18:50
 * @官网 www.weifuwukt.com
 */
@Controller
public class IndexController {

    @Value("${test}")
    private String test;

    @RequestMapping("/")
    @ResponseBody
    public String indexx(){
        return test;
    }
}

