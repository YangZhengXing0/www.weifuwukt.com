package springmvc.annotation.aspect;

import springmvc.annotation.annotation.MyTransaction;
import springmvc.annotation.utils.TransactionUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;

import java.lang.reflect.Method;

/**
 * @author 杨郑兴
 * @Date 2018/12/16 22:13
 * @官网 www.weifuwukt.com
 */
@Component
@Aspect
public class AopMyTransaction {

    @Autowired
    private TransactionUtil transactionUtil;

    //annotation包下的所有包，下的所有类，下的所有方法，下的所有参数
    @Around("execution(* annotation.controller.*.*(..))")
    public void around(ProceedingJoinPoint pjp) throws Throwable{
        //1.获取改方法上是否加上注解
        MyTransaction myTransaction = getMethodMyTransaction(pjp);
        TransactionStatus transactionStatus = null;
        transactionStatus = begin(myTransaction);
        //3.调用目标代理对象方法
        pjp.proceed();
        if(transactionStatus !=null){
            System.out.println("提交事务--");
            transactionUtil.commit(transactionStatus);
        }
    }

    public TransactionStatus begin(MyTransaction myTransaction){
        if(myTransaction == null){
            return null;
        }
        System.out.println("开启事务--");
       return transactionUtil.begin();
    }


    //TransactionUtils 不要实现为单例子： 如果为单例子的话可能会发生线程安全问题
    @AfterThrowing("execution(* annotation.*.*.*(..))")
    public void afterThrowing(){
        System.out.println("回滚事务");
        //TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        transactionUtil.rollback();
    }

    //获取方法上是否存在事务注解
    public MyTransaction getMethodMyTransaction(ProceedingJoinPoint pjp) throws NoSuchMethodException,SecurityException{
        //获取方法名称
        String methodName = pjp.getSignature().getName();
        //获取目标对象
        Class<?> classTarget = pjp.getTarget().getClass();
        //获取目标对象类型
        Class[] par = ((MethodSignature) pjp.getSignature()).getParameterTypes();
        //获取目标对象方法
        Method objMethod = classTarget.getMethod(methodName, par);
        MyTransaction myTransaction = objMethod.getDeclaredAnnotation(MyTransaction.class);
        return myTransaction;
    }
}
