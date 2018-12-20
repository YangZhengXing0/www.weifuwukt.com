package aoplog;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.impl.UserServiceImpl;

/**
 * @author 杨郑兴
 * @Date 2018/12/16 13:05
 * @官网 www.weifuwukt.com
 */
public class AopLogTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext2.xml");
        UserServiceImpl userServiceImpl = (UserServiceImpl)context.getBean("userServiceImpl");
        userServiceImpl.add();
    }
}
