package slf4j.logback.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Enumeration;


/**
 * @author 杨郑兴
 * @Date 2018/12/20 14:04
 * @官网 www.weifuwukt.com
 */
@Component
@Aspect
@Slf4j //lombok插件，就不用写private static final Logger log = LoggerFactory.getLogger(IndexControllerLog.class);
public class IndexControllerLog {
    //lombok插件，就不用写private static final Logger log = LoggerFactory.getLogger(IndexControllerLog.class);
    //private static final Logger log = LoggerFactory.getLogger(IndexControllerLog.class);

    @Autowired
    private HttpServletRequest request;

//    @Autowired
//    private SysLogService sysLogService;

    private Date startTime;
    private Class clazz;
    private Method method;

    @Pointcut("execution(* slf4j.logback.controller.*.*(..))")
    public void webLog() { }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        //获取访问得类
        Class<?> clazz = joinPoint.getTarget().getClass();
        //访问的那个方法
        String methodName = joinPoint.getSignature().getName();
/*        //获取方法的参数数组
        Object[] args = joinPoint.getArgs();
        if(StringUtils.isEmpty(args)&&args.length == 0){
            //获取方法对象
            method = clazz.getMethod(methodName);
        }else{
            Class[] classArgs = new Class[args.length];
            for(int i = 0;i<args.length;i++){
                classArgs[i] = args.getClass();
            }
            //获取方法对象
            clazz.getMethod(methodName,classArgs);
        }*/
        startTime = new Date();
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //获取访问前的时间
        // 记录下请求内容
        log.info("URL : {}", request.getRequestURL().toString());
        log.info("请求类型 : {}", request.getMethod());
        log.info("访问的类、方法 ：{}.{}()", clazz,methodName);
        log.info("IP : {}", request.getRemoteAddr());
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String name = (String) enu.nextElement();
            log.info("{}={}", name, request.getParameter(name));
        }
    }

    @After("webLog()")
    public void after(){
        long time = new Date().getTime() - startTime.getTime();
        log.info("方法执行时间：time={}",time);
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        log.info("返回类容 : " + ret);
    }
}
