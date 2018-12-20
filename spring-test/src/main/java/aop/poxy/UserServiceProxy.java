package aop.poxy;

import aop.UserService;

/**
 * @author 杨郑兴
 * @Date 2018/12/15 19:23
 * @官网 www.weifuwukt.com
 */
public class UserServiceProxy implements UserService {
    private UserService userService;

    public UserServiceProxy(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void add() {
        System.out.println("静态代理--开启事务");
        userService.add();
        System.out.println("静态代理--提交事务");
    }
}
