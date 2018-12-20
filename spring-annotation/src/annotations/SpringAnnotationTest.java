package annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author 杨郑兴
 * @Date 2018/12/16 21:19
 * @官网 www.weifuwukt.com
 */
public class SpringAnnotationTest {

    @MyAnnotation(id = 23,username = "杨郑兴",password ="www.weifuwukt.com")
    public void test(){

    }

    public static void main(String[] args) throws Exception {
        Class<?> forName = Class.forName("annotations.SpringAnnotationTest");
        //获取当前类中（不含继承）的所有方法
        Method[] methods = forName.getDeclaredMethods();
       for (Method method:methods){
           System.out.println("方法："+method.getName());
           //获取方法上是否存在MyAnnotation.class注解
           Annotation annotation = method.getDeclaredAnnotation(MyAnnotation.class);
           if(annotation == null){
                continue;
           }
           System.out.println("id:"+((MyAnnotation) annotation).id());
           System.out.println("username:"+((MyAnnotation) annotation).username());
           System.out.println("password:"+((MyAnnotation) annotation).password());
       }

    }
}
