import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import servler.IndexServlet;

/**
 * @author 杨郑兴
 * @Date 2018/12/22 18:33
 * @官网 www.weifuwukt.com
 */
public class TestTomcat {
    //tomcat启动端口号
    private static int PORT = 8080;
    //tomcat启动项目名称
    private static String CONTEXTPATH = "/weifuwukt";
    //启动加载的servlet,如果是spring mvc内置tomcat,则这里servlet指的就是dispathServlet
    private static String servlet = "IndexServlet";

    public static void main(String[] args) throws LifecycleException{
        // 创建Tomcat服务器
        Tomcat tomcatServer = new Tomcat();
        // 设置Tomcat动端端口号
        tomcatServer.setPort(PORT);
        tomcatServer.getHost().setAutoDeploy(false);
        // 创建Context上下文
        StandardContext standardContext = new StandardContext();
        standardContext.setPath(CONTEXTPATH);
        standardContext.addLifecycleListener(new Tomcat.FixContextListener());
        // tomcat容器添加standardContext
        tomcatServer.getHost().addChild(standardContext);
        // 创建servlet
        tomcatServer.addServlet(CONTEXTPATH, servlet, new IndexServlet());
        // 添加servleturl映射
        standardContext.addServletMappingDecoded("/index", servlet);
        tomcatServer.start();
        System.out.println("tomcat启动...");
        tomcatServer.getServer().await();
    }
}
