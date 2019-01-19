package zookeeper.lock.test2;

/**
 * @author 杨郑兴
 * @Date 2019/1/18 15:42
 * @官网 www.weifuwukt.com
 */
public interface ZookeeperLock {
    //获取锁
    public void getLock();
    //释放锁
    public void unLock();
}
