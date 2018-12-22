package springmvc.emded.tomcat.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import springmvc.emded.tomcat.config.RootConfig;
import springmvc.emded.tomcat.config.WebConfig;

/**
 * AbstractAnnotationConfigDispatcherServletInitializer这个类负责配置DispatcherServlet、
 * 初始化Spring MVC容器和Spring容器。
 * @author 杨郑兴
 * @Date 2018/12/22 18:57
 * @官网 www.weifuwukt.com
 */
public class SpringMVCDispatcherServlet extends AbstractAnnotationConfigDispatcherServletInitializer {

    // 加载根容器,获取Spring应用容器的配置文件，
    // 这里我们给定预先定义的RootConfig.class；
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { RootConfig.class };
    }

    // 加载SpringMVC容器,获取Spring MVC应用容器
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebConfig.class };
    }

    // SpringMVCDispatcherServlet 拦截的请求 /,
    // 指定需要由DispatcherServlet映射的路径，* 这里给定的是"/"，意思是由DispatcherServlet处理所有向该应用发起的请求。
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}
