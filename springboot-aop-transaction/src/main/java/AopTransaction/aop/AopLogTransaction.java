package AopTransaction.aop;

import AopTransaction.utils.TransactionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @author 杨郑兴
 * @Date 2018/12/16 17:07
 * @官网 www.weifuwukt.com
 */
//@Component
@Aspect
public class AopLogTransaction {

    private final String execution="execution(* AopTransaction.controller.UserController.add(..))";

    @Autowired
    private TransactionUtils transactionUtils;

    @Around(execution)
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{

        System.out.println("开启事务");
        TransactionStatus transactionStatus = transactionUtils.begin();
        proceedingJoinPoint.proceed();
        System.out.println("提交事务");
        transactionUtils.commit(transactionStatus);

    }

    //TransactionUtils 不要实现为单例子： 如果为单例子的话可能会发生线程安全问题
    @AfterThrowing(execution)
    public void afterThrowing(){
        System.out.println("回滚事务");
        //获取当前的transactionStatus TransactionAspectSupport.currentTransactionStatus()
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }
}
