package springmvc.annotation.utils;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

/**
 * @author 杨郑兴
 * @Date 2018/12/16 22:15
 * @官网 www.weifuwukt.com
 */
@Component
@Scope("prototype")// 每个事务都是新的实例 目的是为了解决线程安全问题 多实列的
public class TransactionUtil {

    private TransactionStatus transactionStatus;
    // 获取事务源
    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    //开启事务
    public TransactionStatus begin(){
        transactionStatus = dataSourceTransactionManager.getTransaction(new DefaultTransactionAttribute());
        return transactionStatus;
    }


    //提交事务
    public void commit(TransactionStatus transactionStatus){
        dataSourceTransactionManager.commit(transactionStatus);
    }

    //回滚事务
    public void rollback(){
        dataSourceTransactionManager.rollback(transactionStatus);
    }

}
