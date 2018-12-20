package AopTransaction.service.impl;

import AopTransaction.dao.UserDao;
import AopTransaction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 杨郑兴
 * @Date 2018/12/16 16:19
 * @官网 www.weifuwukt.com
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public void add(String username, String password) {
        // 注意事项： 在使用spring事务的时候，service 不要try 最将异常抛出给外层aop 异常通知接受回滚
        try {
           userDao.add(username,password);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("UserServiceImpl 的add()方法 异常");
        }
    }
}
