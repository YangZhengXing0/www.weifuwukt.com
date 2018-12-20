package log.proxy.controller;

import log.proxy.logproxy.HelloCtrollerLog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 杨郑兴
 * @Date 2018/12/12 23:27
 * @官网 www.weifuwukt.com
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        HelloCtrollerLog.logPrint(HelloController.class);
        return "hello";
    }
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
