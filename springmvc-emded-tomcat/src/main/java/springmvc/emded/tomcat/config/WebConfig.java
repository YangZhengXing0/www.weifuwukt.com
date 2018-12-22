package springmvc.emded.tomcat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author 杨郑兴
 * @Date 2018/12/22 19:03
 * @官网 www.weifuwukt.com
 */
@EnableWebMvc//开启spring mvc功能
@ComponentScan("springmvc.emded.tomcat.controller")
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/");
        viewResolver.setSuffix(".jsp");
        //可以在jsp页面中通过${}访问beans
        return viewResolver;
    }
}
