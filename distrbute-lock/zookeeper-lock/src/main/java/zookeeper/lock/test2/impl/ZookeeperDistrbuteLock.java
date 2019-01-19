package zookeeper.lock.test2.impl;

import org.I0Itec.zkclient.IZkDataListener;
import zookeeper.lock.test2.ZookeeperAbstractLock;

/**
 * @author 杨郑兴
 * @Date 2019/1/18 16:03
 * @官网 www.weifuwukt.com
 */
public class ZookeeperDistrbuteLock extends ZookeeperAbstractLock {

    @Override
    protected boolean tryLock() {
        try {
            zkClient.createEphemeral(lockPath);
            return true;
        } catch (Exception e) {
            //由于创建节点没有返回值，不知到是否创建成功
            //但如果存在了，则不会创建成功，会出现错误，所有如果执行到这里 ，则表示创建节点失败，则返回false
            return  false;
        }
    }
    @Override
    protected void waitLock() {
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
}
