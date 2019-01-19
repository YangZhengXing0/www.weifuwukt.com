package zookeeper.lock;

import zookeeper.lock.utlis.OrderNumGenerator;
import zookeeper.lock.test2.ZookeeperLock;
import zookeeper.lock.test2.impl.ZookeeperDistrbuteLock;

/**
 * @author 杨郑兴
 * @Date 2019/1/18 15:41
 * @官网 www.weifuwukt.com
 */
public class ZkLockApplicationTest2 implements Runnable {

    private ZookeeperLock zookeeperLock  = new ZookeeperDistrbuteLock();
    private OrderNumGenerator orderNumGenerator = new OrderNumGenerator();

    public static void main(String[] args) {
        for (int i=0;i<100;i++){
           new Thread(new ZkLockApplicationTest2()).start();
        }

    }

    @Override
    public void run() {
        getID();
    }

    private void getID() {
        try {
            zookeeperLock.getLock();
            String number = orderNumGenerator.getNumber();
            System.out.println(Thread.currentThread().getName()+number);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            zookeeperLock.unLock();
        }
    }
}
