import ioc.xml.dom4j.MyClassPathXmlApplicationContext;
import pojo.User;

/**
 * @author 杨郑兴
 * @Date 2018/12/17 15:23
 * @官网 www.weifuwukt.com
 */
public class Dom4jTest {
    public static void main(String[] args) throws Exception {
        MyClassPathXmlApplicationContext context = new MyClassPathXmlApplicationContext("ioc.xml");
        User user = (User) context.getBean("user");
        System.out.println(user.toString());
    }
}
