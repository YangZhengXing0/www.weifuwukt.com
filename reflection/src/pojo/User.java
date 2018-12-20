package pojo;

/**
 * @author 杨郑兴
 * @Date 2018/12/11 22:47
 * @官网 www.weifuwukt.com
 */
public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        System.out.println("有参构造函数");
        this.username = username;
        this.password = password;
    }

    public User() {
        System.out.println("user 无惨构造");
//        throw  new RuntimeException();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
