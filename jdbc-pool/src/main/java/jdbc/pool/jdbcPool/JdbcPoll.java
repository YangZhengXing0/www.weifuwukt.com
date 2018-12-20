package jdbc.pool.jdbcPool;

import jdbc.pool.exception.IConnectionPoolException;
import jdbc.pool.pojo.DbBean;
import jdbc.pool.utils.IConnectionPoolEnum;
import org.springframework.util.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

/**
 * @author 杨郑兴
 * @Date 2018/12/19 13:50
 * @官网 www.weifuwukt.com
 */
public class JdbcPoll  implements IConnectionPool{

    //初始化线程容器-使用线程安全的集合-空闲线程，没有被使用的连接存放
    private List<Connection> freeConnection = new Vector<Connection>();
    //使用线程安全的集合-互动线程，容器正在使用的连接
    private List<Connection> activeConnection = new Vector<Connection>();

    private DbBean dbBean;

    private volatile Integer count = 0;

    public JdbcPoll(DbBean dbBean) {
        this.dbBean = dbBean;
        init();
    }

    //1.初始化线程池(初始化空闲线程)
    public void init(){
        //1.1 获取配置类
        if(StringUtils.isEmpty(this.dbBean)){
            throw new IConnectionPoolException(IConnectionPoolEnum.NOT_FOUND_DBBEAN.getMessage());
        }

        //1.2 初始化线程连接
        for(int i = 0; i < dbBean.getInitConnections();i++){
            //1.3 创建Connection连接
            Connection connection = newConnection();
            if(!StringUtils.isEmpty(connection)){
                freeConnection.add(connection);
            }
        }

    }

    //创建Connection连接
    public synchronized Connection newConnection(){
        try {
            Class.forName(dbBean.getDriverName());
            Connection connection = DriverManager.getConnection(dbBean.getUrl(), dbBean.getUserName(), dbBean.getPassword());
            count++;
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //2 获取连接
    @Override
    public Connection getConnection() {
        //思考：2.1 怎么知道当前创建的连接>最大连接数
        Connection connection = null;
        if(count < dbBean.getMaxActiveConnections()){
            //小于最大活动连接数
            //2.2 判断空线程是否有数据
            if(freeConnection.size()>0){
                //2.2.1 空闲线程有存在的连接
                //remove():拿到再删除
                connection = freeConnection.remove(0);
            }else{
                //2.3 空闲线程没有连接，则创建新的连接
                connection = newConnection();
            }
            //判断连接是否可用
            boolean available = isAvailable(connection);
            if(available){
                //存放在活动线程池
                activeConnection.add(connection);
            }else {
                count--;
                connection = getConnection();//失败了，则重试
            }
        }else{
            //大于最大活动连接数，进行连接
            try {
                wait(dbBean.getConnTimeOut());
                //重试
                connection = getConnection();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    //判断连接是否可用
    public boolean isAvailable(Connection connection){
        try {
            if(connection == null || connection.isClosed()){
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


    //3 释放资源
    @Override
    public void releaseConnection(Connection connection) {
        //3.1 判断连接是否可用
        if(isAvailable(connection)){
            if(freeConnection.size() < dbBean.getMaxConnections()){
                //空闲线程没有满
                freeConnection.add(connection);
            }else{
                //空闲线程满了
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            activeConnection.remove(count);
            count--;
            notifyAll();
        }
        //3.2 判断空闲线程是否大于活动线程
    }
    //
}
