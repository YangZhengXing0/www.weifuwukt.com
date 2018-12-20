package ioc.xml;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.A;
import pojo.Book;
import pojo.User;

/**
 * @author 杨郑兴
 * @Date 2018/12/15 13:45
 * @官网 www.weifuwukt.com
 */
public class IocTest1 {

    @Test
    public void ioc1(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        User user = (User)context.getBean("user");
        System.out.println(user.toString());
    }

    @Test
    public void ioc2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        Book book1 = (Book)context.getBean("book1");
        System.out.println(book1.toString());

        Book book2 = (Book)context.getBean("book2");
        System.out.println(book2.toString());
    }
    @Test
    public void ioc3(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext1.xml");
        Book book1 = (Book)context.getBean("book1");
        System.out.println(book1.toString());
        A a = new A();
        a.setA("2aaa");
        book1.setA(a);
        System.out.println(book1.toString());
    }
}
