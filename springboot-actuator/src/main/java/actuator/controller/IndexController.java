package actuator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 杨郑兴
 * @Date 2018/12/22 14:17
 * @官网 www.weifuwukt.com
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index(String username){

        return "ok";
    }
}
