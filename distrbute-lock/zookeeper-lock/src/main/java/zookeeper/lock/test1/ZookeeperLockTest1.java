package zookeeper.lock.test1;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.CountDownLatch;

/**
 * 使用模板方法
 * @author 杨郑兴
 * @Date 2019/1/18 15:44
 * @官网 www.weifuwukt.com
 */
public class ZookeeperLockTest1 {

    //本类可以使用
    private static final String CONNETI = "127.0.0.1:2181";
    //本类及其子类可以使用
    protected ZkClient zkClient = new ZkClient(CONNETI);
    protected String lockPath = "/lockPath";
    //使用java并发包，信号量技术，控制zk连接成功后，开始创建节点，否则情况下会等待，1表示当前计数器为1
    protected CountDownLatch countDownLatch = new CountDownLatch(1);

    //获取锁
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
    public  void waitLock(){
        //监听事件通知
        IZkDataListener iZkDataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object o) throws Exception {
                //节点发送改变，如修改
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                //节点被删除
                if(dataPath !=null){
                    countDownLatch.countDown();//调用该方法的时候会做减1，计数器一旦为0，countDownLatch.await()后面的代码就会执行
                }
            }
        };
        zkClient.subscribeDataChanges(lockPath, iZkDataListener);

        if(zkClient.exists(lockPath)){
            try {
                countDownLatch.await();//如果我的计数器不是为0的话，会一直等待的
            } catch (Exception e) {
            }
        }
        //为了不影响程序的效率，建议删除该监听事件
        zkClient.unsubscribeDataChanges(lockPath,iZkDataListener);
    }

    //获取锁的资源，如果能够获取锁成功，则返回true,否则返回false
    public boolean tryLock(){
        try {
            zkClient.createEphemeral(lockPath);
            return true;
        } catch (Exception e) {
            //由于创建节点没有返回值，不知到是否创建成功
            //但如果存在了，则不会创建成功，会出现错误，所有如果执行到这里 ，则表示创建节点失败，则返回false
            return  false;
        }
    }
    //释放锁
    public void unLock() {
        //当程序执行完成时候，直接关闭连接
        if (zkClient != null){
            zkClient.close();
            System.out.println("......释放锁成功.....");
        }

    }
}
