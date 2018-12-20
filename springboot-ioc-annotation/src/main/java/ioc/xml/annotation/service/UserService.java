package ioc.xml.annotation.service;

import ioc.xml.annotation.annotation.MyResource;
import ioc.xml.annotation.annotation.MyService;

/**
 * @author 杨郑兴
 * @Date 2018/12/17 17:21
 * @官网 www.weifuwukt.com
 */
@MyService
public class UserService {

    @MyResource
    public Book book;

    public UserService() {
        System.out.println("userService 无惨构造");
    }

    public Book getBook() {
        return book;
    }
}
