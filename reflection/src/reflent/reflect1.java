package reflent;

import pojo.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author 杨郑兴
 * @Date 2018/12/11 22:47
 * @官网 www.weifuwukt.com
 */
public class reflect1 {
    public static void main(String[] args) throws Exception {
//        User user = new User();
        Class<?> forName = Class.forName("pojo.User");
        User user = (User)forName.newInstance();
        user.setUsername("Class.forName创建的user对象");
        System.out.println(user.getUsername());
        Method[] methods = forName.getMethods();
        //Arrays.stream(methods).forEach(e -> System.out.println(e.getName()));
        //Arrays.stream(forName.getDeclaredFields()).forEach(e-> System.out.println(e.getName()));

        Constructor<?> constructor = forName.getConstructor(String.class, String.class);
        User user1 = (User)constructor.newInstance("yangzhengxing", "123");
       System.out.println(user1.toString());



    }
}
