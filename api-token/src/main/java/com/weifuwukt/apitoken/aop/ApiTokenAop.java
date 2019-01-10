package com.weifuwukt.apitoken.aop;

import com.weifuwukt.apitoken.annotation.ApiToken;
import com.weifuwukt.apitoken.utils.IdWorker;
import com.weifuwukt.apitoken.utils.RedisTokenUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 杨郑兴
 * @Date 2019/1/10 19:36
 * @官网 www.weifuwukt.com
 */
@Aspect
@Component
public class ApiTokenAop {

    public static final String HEADER = "header";
    public static final String FROM = "from";

    @Autowired
    private IdWorker idWorker;
    @Autowired
    private RedisTokenUtils redisTokenUtils;

    @Pointcut("execution(* com.weifuwukt.apitoken.controller.IndexController.insert*(..))")
    public void ApiTokenPointcut(){}

    //生成token,并设置到request中
//    @Before("ApiTokenPointcut()")
//    public void before(JoinPoint joinPoint){
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        ApiToken apiToken = signature.getMethod().getDeclaredAnnotation(ApiToken.class);
//        if(!StringUtils.isEmpty(apiToken)){
//            setRequestApiToken();
//        }
//    }

    @Around("ApiTokenPointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        ApiToken apiToken = signature.getMethod().getDeclaredAnnotation(ApiToken.class);
        if(!StringUtils.isEmpty(apiToken)){
            //方法上存在注解apiToken
            return checkApiToken(pjp,apiToken);
        }
        //如果方法上没有@ApiToken,则放行
        return pjp.proceed();
    }

    //把生成的token值，设置到request中
    public void setRequestApiToken(){
        String token = redisTokenUtils.getToken();
        HttpServletRequest request = getRequest();
        request.setAttribute("token",token);
    }

    //获取当前request
    public HttpServletRequest getRequest(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        return request;
    }

    //验证Token
    public Object checkApiToken(ProceedingJoinPoint pjp, ApiToken apiToken) throws Throwable{

        HttpServletRequest request = getRequest();
        String type = apiToken.type();
        if(StringUtils.isEmpty(type)){
            throw new RuntimeException("@ApiToken注解的type参数为null");
        }

        String token = null;

        if(type.equals(ApiTokenAop.HEADER)){
            //获取放在请求头中的令牌
            token = request.getHeader("token");
        }else if(type.equals(ApiTokenAop.FROM)){
            //获取放在from表单中的令牌
            token = request.getParameter("token");
        }else {
            throw new RuntimeException("@ApiToken注解的type参数需为HEADER和FROM中的一个");
        }

        if(StringUtils.isEmpty(token)){
            throw new RuntimeException("token参数为空");
        }
        //redis中是否查到token redis中key和value都是同一个token值，所以这里传token把而不是"token"
        if(!redisTokenUtils.findToken(token)){
            //redis中查不到token
            throw new RuntimeException("请勿重新提交");
        }
        //redis中查到token 则放行
        return pjp.proceed();
    }
}
