package aop.impl;

import aop.UserService;

/**
 * @author 杨郑兴
 * @Date 2018/12/15 19:20
 * @官网 www.weifuwukt.com
 */
public class UserServiceImpl implements UserService {
    @Override
    public void add() {
        System.out.println("去数据库中添加数据");
    }
}
