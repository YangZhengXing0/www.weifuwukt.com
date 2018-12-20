package proxy.staticProxy.impl;

import proxy.staticProxy.IuUserDao;

/**
 * @author 杨郑兴
 * @Date 2018/12/12 19:50
 * @官网 www.weifuwukt.com
 */
public class IuUserDaoImplProxy implements IuUserDao {

    private IuUserDao iuUserDao;

    public IuUserDaoImplProxy(IuUserDao iuUserDao) {
        this.iuUserDao = iuUserDao;
    }

    public void add() {
        System.out.println("代理执行add方法之前");
        iuUserDao.add();
        System.out.println("代理执行add方法之后");
    }
}
