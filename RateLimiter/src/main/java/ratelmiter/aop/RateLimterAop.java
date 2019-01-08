package ratelmiter.aop;

import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ratelmiter.annatation.MyRateLimter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 1.判断请求方法上是否有@MyRateLimiter注解
 * 2.如果方法上存在@MyRateLimiter注解，则使用反射技术获取方法上的@MyRateLimiter注解的参数值
 * 3.调用原生的RateLimiter代码创建令牌桶
 * 4.如果令牌超时，则直接调用服务降级方法
 * 5.如果获取到令牌，则直接进入请求方法中
 * @author 杨郑兴
 * @Date 2019/1/8 22:38
 * @官网 www.weifuwukt.com
 */
@Aspect
@Component
public class RateLimterAop {

    private ConcurrentHashMap<String,RateLimiter> hashMap = new ConcurrentHashMap<String,RateLimiter>();
    //定义切入点
    @Pointcut("execution(* ratelmiter.controller.*.*(..))")
    public void pointcut(){}

    @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        //1.判断请求方法上是否有@MyRateLimiter注解
        MyRateLimter myRateLimter =getMyRateLimterAnnotation(proceedingJoinPoint);
        if (StringUtils.isEmpty(myRateLimter)){
            Object proceed = proceedingJoinPoint.proceed();
            return proceed;
        }
        //2.如果方法上存在@MyRateLimiter注解，则使用反射技术获取方法上的@MyRateLimiter注解的参数值
        double createRate = myRateLimter.CreateRate();
        long timeout = myRateLimter.timeout();
        // 3.调用原生的RateLimiter代码创建令牌桶,保证每一个请求对应都是单列的RateLimiter
        //获取request对象
        HttpServletRequest request = getRequest();
        //获取请求URI,因为controller中，每个方法上对应了一个请求的URI,
        String requestURI = getRequestURI(request);
        //一个起请求uri对应一个RateLimiter
        boolean isExistURI = hashMap.containsKey(requestURI);
        RateLimiter rateLimiter = null;
        if(isExistURI){
            rateLimiter = hashMap.get(requestURI);
        }else{
            rateLimiter = RateLimiter.create(createRate);
            hashMap.put(requestURI,rateLimiter);
        }
        boolean acquire= rateLimiter.tryAcquire(timeout, TimeUnit.MILLISECONDS);
        if(!acquire){
            //在规定的createRate毫秒内，没有拿到令牌，则走服务降级
            fallback();
            return null;
        }
        //拿到令牌了,放行，直接进入请求方法中
        Object proceed = proceedingJoinPoint.proceed();
        return  proceed;
    }
    //服务降级
    public void fallback() throws IOException {
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = attributes.getResponse();
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        try {
            writer.println("系统繁忙！请稍后再重试");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }

    //获取@MyRateLimter注解
    public MyRateLimter getMyRateLimterAnnotation(ProceedingJoinPoint proceedingJoinPoint){
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        MyRateLimter myRateLimter = methodSignature.getMethod().getDeclaredAnnotation(MyRateLimter.class);
        return myRateLimter;
    }

    //获取request对象
    public HttpServletRequest getRequest(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        return request;
    }

    //获取请求URI
    public String getRequestURI(HttpServletRequest request){
        return request.getRequestURI();
    }
}
