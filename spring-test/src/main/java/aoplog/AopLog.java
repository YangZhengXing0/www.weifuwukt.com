package aoplog;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author 杨郑兴
 * @Date 2018/12/15 19:40
 * @官网 www.weifuwukt.com
 */
@Component
@Aspect//切面
public class AopLog {
    //切入点
    private final String EXECUTION="execution(* service.impl.UserServiceImpl.add(..))";

    //前置通知:目标方法之前执行
    @Before(EXECUTION)
    public void before(){
        System.out.println("前置通知");
    }

    //后置通知:目标方法之后执行（始终执行)
    @After(EXECUTION)
    public void after(){
        System.out.println("后置通知");
    }

    //返回后通知： 执行方法结束前执行(异常不执行)
    @AfterReturning(EXECUTION)
    public void afterReturning(){
        System.out.println("运行通知");
    }

    //异常通知:  出现异常时候执行
    @AfterThrowing(EXECUTION)
    public void afterThrowing(){
        System.out.println("异常通知");
    }
    //环绕通知： 环绕目标方法执行
    @Around(EXECUTION)
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        System.out.println("环绕通知开始");
        proceedingJoinPoint.proceed();
        System.out.println("环绕通知结束");
    }
}
