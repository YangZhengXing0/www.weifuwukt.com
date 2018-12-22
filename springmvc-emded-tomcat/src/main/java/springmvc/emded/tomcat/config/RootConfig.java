package springmvc.emded.tomcat.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 根容器
 * @author 杨郑兴
 * @Date 2018/12/22 19:02
 * @官网 www.weifuwukt.com
 */
@Configuration
@ComponentScan(basePackages = "springmvc.emded.tomcat")
public class RootConfig {
}
