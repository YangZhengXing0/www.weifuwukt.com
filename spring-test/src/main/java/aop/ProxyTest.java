package aop;

import aop.impl.UserServiceImpl;
import aop.poxy.UserServiceProxy;

/**
 * @author 杨郑兴
 * @Date 2018/12/15 19:25
 * @官网 www.weifuwukt.com
 */
public class ProxyTest {
    public static void main(String[] args) {
        UserServiceProxy userServiceProxy = new UserServiceProxy(new UserServiceImpl());
        userServiceProxy.add();
    }
}
