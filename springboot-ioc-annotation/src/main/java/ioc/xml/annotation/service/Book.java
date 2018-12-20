package ioc.xml.annotation.service;

import ioc.xml.annotation.annotation.MyService;

/**
 * @author 杨郑兴
 * @Date 2018/12/17 19:06
 * @官网 www.weifuwukt.com
 */
@MyService
public class Book {
    public Book() {
        System.out.println("book 无参构造");
    }
}
