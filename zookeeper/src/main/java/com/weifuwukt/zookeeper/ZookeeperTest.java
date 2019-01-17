package com.weifuwukt.zookeeper;

import org.apache.zookeeper.*;
import org.springframework.util.StringUtils;

import java.util.concurrent.CountDownLatch;


/**
 * @author 杨郑兴
 * @Date 2019/1/17 14:30
 * @官网 www.weifuwukt.com
 */
public class ZookeeperTest {
    //zookeeper连接地址
    private static final String con = "127.0.0.1:2181";
    //session超时多久
    private static final int session_timeout = 5000;
    //使用java并发包，信号量技术，控制zk连接成功后，开始创建节点，否则情况下会等待，1表示当前计数器为1
    private static final CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper = null;
        try {
            //创建了一个连接
            zooKeeper = new ZooKeeper(con, session_timeout, new Watcher() {
                //监听节点是否发送生变化了
                @Override
                public void process(WatchedEvent event) {
                    //监听节点是否发生变化
                    //获取事件状态
                    Event.KeeperState keeperState = event.getState();
                    //获取事件类型
                    Event.EventType eventType = event.getType();
                    //如果为同步连接
                    if (Event.KeeperState.SyncConnected == keeperState) {
                        //事件监听
                        if (Event.EventType.None == eventType) {
                            countDownLatch.countDown();//调用该方法的时候会做减1;
                            System.out.println("zookeeper 启动连接...");
                        }
                        if(Event.EventType.NodeCreated == eventType){
                            System.out.println("节点创建");
                        }
                        if(Event.EventType.NodeDataChanged == eventType){
                            System.out.println("节点数据发生改变");
                        }
                        if(Event.EventType.NodeDeleted == eventType){
                            System.out.println("节点删除");
                        }
                       /* if(Event.EventType.NodeChildrenChanged == eventType){
                            System.out.println("节点的子节点发生改变");
                        }*/
                    }
                }
            });
            countDownLatch.await();//如果我的计数器不是为0的话，会一直等待的
            String path="/yzx12345";
            zooKeeper.exists(path,true);//设置为true，则表示允许事件监听
            // ZooDefs.Ids.OPEN_ACL_UNSAFE:表示这个节点事开放的，任何权限都可以操作
            String result= zooKeeper.create(path,"yzx123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            //String result= zooKeeper.create(path,"yzx123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            zooKeeper.delete(path,0);
            if(StringUtils.isEmpty(result)){
                System.out.println("result=null");
            }
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(zooKeeper != null){
                zooKeeper.close();
            }
        }
    }
}
