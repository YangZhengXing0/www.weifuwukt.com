package pojo;

import org.springframework.stereotype.Component;

/**
 * @author 杨郑兴
 * @Date 2018/12/15 14:43
 * @官网 www.weifuwukt.com
 */
public class A {
    private String a;


    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return "A{" +
                "a='" + a + '\'' +
                '}';
    }
}
