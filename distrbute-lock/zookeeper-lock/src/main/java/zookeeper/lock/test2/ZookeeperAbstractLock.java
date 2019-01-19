package zookeeper.lock.test2;

import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.CountDownLatch;

/**
 * 使用模板方法
 * @author 杨郑兴
 * @Date 2019/1/18 15:44
 * @官网 www.weifuwukt.com
 */
public abstract class ZookeeperAbstractLock implements ZookeeperLock{

    //本类可以使用
    private static final String CONNETI = "127.0.0.1:2181";
    //本类及其子类可以使用
    protected ZkClient zkClient = new ZkClient(CONNETI);
    protected String lockPath = "/lockPath";
    //使用java并发包，信号量技术，控制zk连接成功后，开始创建节点，否则情况下会等待，1表示当前计数器为1
    protected CountDownLatch countDownLatch = new CountDownLatch(1);

    //获取锁
    @Override
    public void getLock() {
        //创建成功，执行业务，否则执行等待
        if(tryLock()){
            System.out.println("获取锁资源成功");
        }else {
            //进行等待
            waitLock();
            //3.使用事件通知，监听该节点是否被删除，如果被删除则重新进入获取锁资源
            getLock();
        }

    }
    //如果创建节点失败，进行等待，使用事件通知监听该节点是否被删除，如果被删除的话，重新进入获取锁的资源
    protected abstract void waitLock();
    //获取锁的资源，如果能够获取锁成功，则返回true,否则返回false
    protected abstract boolean tryLock();
    //释放锁
    @Override
    public void unLock() {
        //当程序执行完成时候，直接关闭连接
        if (zkClient != null){
            zkClient.close();
            System.out.println("......释放锁成功.....");
        }

    }
}
