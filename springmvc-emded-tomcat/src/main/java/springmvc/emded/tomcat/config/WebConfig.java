package springmvc.emded.tomcat.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author 杨郑兴
 * @Date 2018/12/22 19:03
 * @官网 www.weifuwukt.com
 */
@EnableWebMvc//开启spring mvc功能
@ComponentScan("springmvc.emded.tomcat.controller")
public class WebConfig extends WebMvcConfigurationSupport {
}
