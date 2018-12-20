package pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 杨郑兴
 * @Date 2018/12/15 14:07
 * @官网 www.weifuwukt.com
 */
public class Book {
    private  A a;
    private User user;
    private String num1;
    private Integer num2;

    public Book() {
        System.out.println("Book 无惨构造函数");
    }

    public Book(A a, User user, String num1) {
        this.a = a;
        this.user = user;
        this.num1 = num1;
    }

    public Book(A a, User user, Integer num2) {
        this.a = a;
        this.user = user;
        this.num2 = num2;
    }

    @Override
    public String toString() {
        return "Book{" +
                "a=" + a +
                ", user=" + user +
                ", num1='" + num1 + '\'' +
                ", num2=" + num2 +
                '}';
    }

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNum1() {
        return num1;
    }

    public void setNum1(String num1) {
        this.num1 = num1;
    }

    public Integer getNum2() {
        return num2;
    }

    public void setNum2(Integer num2) {
        this.num2 = num2;
    }
}
