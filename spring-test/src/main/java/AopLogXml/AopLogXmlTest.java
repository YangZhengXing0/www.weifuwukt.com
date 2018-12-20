package AopLogXml;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.impl.UserServiceImpl;

/**
 * @author 杨郑兴
 * @Date 2018/12/16 13:39
 * @官网 www.weifuwukt.com
 */
public class AopLogXmlTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext3.xml");
        UserServiceImpl userServiceImpl = (UserServiceImpl)context.getBean("userServiceImpl");
        userServiceImpl.add();

    }
}
