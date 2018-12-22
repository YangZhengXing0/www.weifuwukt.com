package springmvc.emded.tomcat;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import javax.servlet.ServletException;
import java.io.File;

/**
 * @author 杨郑兴
 * @Date 2018/12/22 19:11
 * @官网 www.weifuwukt.com
 */
public class TomcatStart {
    //tomcat启动端口号
    private static int PORT = 8080;

    public static void main(String[] args) throws ServletException, LifecycleException {
        start();
    }

    public static void start()  throws ServletException, LifecycleException{
        // 创建Tomcat容器
        Tomcat tomcatServer = new Tomcat();
        // 端口号设置
        tomcatServer.setPort(PORT);
        // 读取项目路径,还有比如静态文件，这个项目没有静态文件，所以就没有去读取静态文件
        StandardContext ctx = (StandardContext) tomcatServer.addWebapp("/", new File("src/main")
                .getAbsolutePath());
        // 禁止重新载入
        ctx.setReloadable(false);
        // class文件读取地址
        File additionWebInfClasses = new File("target/classes");
        // 创建WebRoot
        WebResourceRoot resources = new StandardRoot(ctx);
        // tomcat内部读取Class执行
        resources.addPreResources(
                new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(), "/"));
        tomcatServer.start();
        // 异步等待请求执行
        tomcatServer.getServer().await();
    }
}
