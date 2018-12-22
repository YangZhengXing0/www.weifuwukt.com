package mybatis.pagehelper.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author 杨郑兴
 * @Date 2018/12/20 21:38
 * @官网 www.weifuwukt.com
 */
@Data
@Component
public class User {
    private Integer id;
    private String username;
    private String password;
}

