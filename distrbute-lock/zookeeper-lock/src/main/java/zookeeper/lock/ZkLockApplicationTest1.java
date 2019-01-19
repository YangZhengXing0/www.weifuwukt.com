package zookeeper.lock;

import zookeeper.lock.test1.ZookeeperLockTest1;
import zookeeper.lock.utlis.OrderNumGenerator;

/**
 * @author 杨郑兴
 * @Date 2019/1/18 15:41
 * @官网 www.weifuwukt.com
 */
public class ZkLockApplicationTest1 implements Runnable {

    private ZookeeperLockTest1 zookeeperLockTest1  = new ZookeeperLockTest1();
    private OrderNumGenerator orderNumGenerator = new OrderNumGenerator();

    public static void main(String[] args) {
        for (int i=0;i<100;i++){
           new Thread(new ZkLockApplicationTest1()).start();
        }

    }

    @Override
    public void run() {
        getID();
    }

    private void getID() {
        try {
            zookeeperLockTest1.getLock();
            String number = orderNumGenerator.getNumber();
            System.out.println(Thread.currentThread().getName()+number);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            zookeeperLockTest1.unLock();
        }
    }
}
