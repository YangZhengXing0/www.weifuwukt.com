package ratelmiter.annatation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解 MyRateLimter
 * @author 杨郑兴
 * @Date 2019/1/8 22:35
 * @官网 www.weifuwukt.com
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyRateLimter {
    //表示每秒创建的令牌数,默认一秒创建一个令牌
    double CreateRate() default 1.0;
    /*表示规定的多少毫秒之内，如果没有获取到令牌的话，则直接走服务降级
     *尝试获取令牌:如果在500毫秒内，没有获取到令牌的话，则直接走服务降级
     *boolean acquire = rateLimiter.tryAcquire(500, TimeUnit.MILLISECONDS);
     */
    //这里默认是尝试获取令牌时间最多500毫秒
    long timeout() default 500;
}
