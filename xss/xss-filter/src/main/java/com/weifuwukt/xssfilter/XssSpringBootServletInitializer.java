package com.weifuwukt.xssfilter;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author 杨郑兴
 * @Date 2019/1/10 2:44
 * @官网 www.weifuwukt.com
 */
public class XssSpringBootServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(XssFilterApplication.class);
    }
}
