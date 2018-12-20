package service.impl;

import aop.UserService;
import org.springframework.stereotype.Service;

/**
 * @author 杨郑兴
 * @Date 2018/12/16 12:43
 * @官网 www.weifuwukt.com
 */
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
    @Override
    public void add() {
        System.out.println("-------添加数据中-----");
        throw  new RuntimeException();
    }
}
