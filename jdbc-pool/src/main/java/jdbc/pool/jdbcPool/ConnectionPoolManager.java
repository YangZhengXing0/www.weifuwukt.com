package jdbc.pool.jdbcPool;

import jdbc.pool.pojo.DbBean;

import java.sql.Connection;


/**
 * @author 杨郑兴
 * @Date 2018/12/19 17:20
 * @官网 www.weifuwukt.com
 */
//管理线程池
public class ConnectionPoolManager {

    private static DbBean dbBean =new DbBean();
    private static JdbcPoll connectionPool = new JdbcPoll(dbBean);

    //获取连接（重复利用机制）
    public static Connection getConnection(){
        return connectionPool.getConnection();
    }

    //释放连接（可回收机制）
    public static void releaseConnection(Connection connection){
        connectionPool.releaseConnection(connection);
    }
}
