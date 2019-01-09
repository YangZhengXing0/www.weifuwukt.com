package com.weifuwukt.xssfilter.controller;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 杨郑兴
 * @Date 2019/1/10 2:19
 * @官网 www.weifuwukt.com
 */
@Controller
public class XssController {

    @PostMapping("/xssfilter")
    public String home(String info, Model model){
        info = StringEscapeUtils.escapeHtml4(info);
        System.out.println(info);
        model.addAttribute("info",info);
        return "home";
    }
    @RequestMapping("/")
    public String index(){
        return "home";
    }

}
