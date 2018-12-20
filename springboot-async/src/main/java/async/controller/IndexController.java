package async.controller;

import async.service.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 杨郑兴
 * @Date 2018/12/20 17:19
 * @官网 www.weifuwukt.com
 */
@Controller
@Slf4j
public class IndexController {
    @Autowired
    private IndexService indexService;

    //@Async实现异步调用，返回值是不能得到的，既然async=null
    @RequestMapping("/index")
    public String index(){
        log.info("1");
        String async = indexService.async();
        log.info("async={}",async);
        log.info("4");
        return "index.html";
    }

    //模拟实现异步调用，这里的返回结果，被调用者  可接受到
    @RequestMapping("/hello")
    public String hello(){
        log.info("1");
        String async = indexService.hello();
        log.info("async={}",async);
        log.info("4");
        return "index.html";
    }

}
