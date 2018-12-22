package mybatis.pagehelper.service;


import com.github.pagehelper.PageInfo;
import mybatis.pagehelper.pojo.User;

import java.util.List;

/**
 * @author 杨郑兴
 * @Date 2018/12/21 12:15
 * @官网 www.weifuwukt.com
 */
public interface UserService {
    int insert(User user);

    int delete(Integer id);

    int update(User user);

    User findByName(String username);

    PageInfo<User> findAll(int page, int pageSize);
}
