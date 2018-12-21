package jsp.controller;

import jsp.SpringBootJsp;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//打war包一定要有整个类，springboot2.0以后推荐使用，否则找不到页面
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootJsp.class);
    }

}

