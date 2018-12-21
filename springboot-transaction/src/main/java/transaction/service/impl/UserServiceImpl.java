package transaction.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import transaction.dao.UserDao;
import transaction.pojo.User;
import transaction.service.UserService;

/**
 * @author 杨郑兴
 * @Date 2018/12/21 12:16
 * @官网 www.weifuwukt.com
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public int insert(User user) {
        log.info(user.toString());
        int i = userDao.insert(user);
        return i;
    }

    @Override
    public int delete(Integer id) {
        int i = userDao.delete(id);
        return i;
    }

    @Override
    public int update(User user) {
        int i = userDao.update(user);
        return i;
    }

    @Override
    public User findByName(String username) {
        User user = userDao.findByName(username);
        return user;
    }
}
