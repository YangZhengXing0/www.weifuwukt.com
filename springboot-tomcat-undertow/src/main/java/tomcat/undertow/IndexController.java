package tomcat.undertow;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 杨郑兴
 * @Date 2018/12/21 22:23
 * @官网 www.weifuwukt.com
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        System.out.println("ok");
        return "ok";
    }
}
