package slf4j.logback.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 杨郑兴
 * @Date 2018/12/20 14:03
 * @官网 www.weifuwukt.com
 */
@Controller
@Slf4j
public class IndexController {
    @RequestMapping("/index")
//  @ResponseBody
    public String index(String username, String password, ModelAndView mv){
        HashMap<String, String> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        mv.addObject("map",map);
        return "index.html";
    }
}
