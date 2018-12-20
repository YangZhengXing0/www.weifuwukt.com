package jdbc.pool.jdbcPool;

import java.sql.Connection;

/**
 * @author 杨郑兴
 * @Date 2018/12/19 15:26
 * @官网 www.weifuwukt.com
 */
//数据库连接池
public interface IConnectionPool {
    //获取连接(重复利用机制)
    public Connection getConnection();

    //释放连接(可回收机制)
    public void releaseConnection(Connection connection);


}
