package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 杨郑兴
 * @Date 2018/12/16 21:19
 * @官网 www.weifuwukt.com
 */
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    int id() default 1;
    String username() default "yzx";
    String password() ;
}
