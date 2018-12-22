package mybatis.pagehelper.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import mybatis.pagehelper.dao.UserDao;
import mybatis.pagehelper.pojo.User;
import mybatis.pagehelper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public PageInfo<User> findAll(int page,int pageSize) {

        //底层实现原理：才有改写语句
        PageHelper.startPage(page, pageSize);
        List<User> userList = userDao.findAll();
        //返回给客户端展示
        PageInfo<User> userPageInfo = new PageInfo<>(userList);
        return userPageInfo;
    }
}
