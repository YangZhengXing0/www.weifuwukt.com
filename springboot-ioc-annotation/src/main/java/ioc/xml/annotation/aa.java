package ioc.xml.annotation;

import ioc.xml.annotation.annotation.MyResource;
import ioc.xml.annotation.service.UserService;

import java.lang.reflect.Field;

/**
 * @author 杨郑兴
 * @Date 2018/12/17 20:03
 * @官网 www.weifuwukt.com
 */
public class aa {

    public static void main(String[] args) throws Exception{
        Class<?> forName = Class.forName("ioc.xml.annotation.service.UserService");
        Class<? extends UserService> aClass = new UserService().getClass();
        System.out.println("forName="+forName);
        System.out.println("aClass="+aClass);
        Field[] forNameFields = forName.getDeclaredFields();
        Field[] aClassdFields = aClass.getDeclaredFields();


        System.out.println("forName------");
        for (Field field:forNameFields){
            System.out.println(field.getName());
        }
        System.out.println("aClass------");
        for (Field field:aClassdFields){
            MyResource annotation = field.getAnnotation(MyResource.class);
            System.out.println(field.getName());
        }
    }
}
