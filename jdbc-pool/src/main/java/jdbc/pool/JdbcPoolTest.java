package jdbc.pool;

import jdbc.pool.jdbcPool.ConnectionPoolManager;

import java.sql.Connection;
import java.util.RandomAccess;

/**
 * @author 杨郑兴
 * @Date 2018/12/19 17:25
 * @官网 www.weifuwukt.com
 */
public class JdbcPoolTest {
    public static void main(String[] args) {
        ThreadConnection threadConnection = new ThreadConnection();
        for(int i = 1;i<3;i++){
            Thread thread = new Thread(threadConnection,"线程i:"+i);
            thread.start();
        }
    }
}
class ThreadConnection implements Runnable{

    @Override
    public void run() {
        for(int i=0; i<10;i++){
            Connection connection = ConnectionPoolManager.getConnection();
            System.out.println(Thread.currentThread().getName()+",connect:"+connection);
            ConnectionPoolManager.releaseConnection(connection);
        }
    }
}
