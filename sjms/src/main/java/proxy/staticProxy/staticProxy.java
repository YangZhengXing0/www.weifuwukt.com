package proxy.staticProxy;

import proxy.staticProxy.impl.IuUserDaoImpl;
import proxy.staticProxy.impl.IuUserDaoImplProxy;

/**
 * 静态代理
 *
 * @author 杨郑兴
 * @Date 2018/12/12 19:45
 * @官网 www.weifuwukt.com
 */
public class staticProxy {
    public static void main(String[] args) {
        IuUserDao iuUserDao = new IuUserDaoImpl();
        IuUserDaoImplProxy iuUserDaoImplProxy = new IuUserDaoImplProxy(iuUserDao);
        iuUserDaoImplProxy.add();
    }
}
