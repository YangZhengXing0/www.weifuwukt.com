package ioc.xml.annotation;

import ioc.xml.annotation.annotation.MyClassPathXmlApplicationContext;
import ioc.xml.annotation.service.Book;
import ioc.xml.annotation.service.UserService;

/**
 * @author 杨郑兴
 * @Date 2018/12/17 17:07
 * @官网 www.weifuwukt.com
 */
public class IocAnnotationTest {
    public static void main(String[] args) throws Exception {
        MyClassPathXmlApplicationContext app = new MyClassPathXmlApplicationContext("ioc.xml.annotation.service");
        UserService userService = (UserService)app.getBean("userService");
        Book book = (Book)app.getBean("book");
        System.out.println(userService.toString());
        System.out.println("userService属性："+userService.getBook());
        System.out.println(book.toString());
    }
}
