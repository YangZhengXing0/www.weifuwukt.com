package single;

/**
 * 双检锁/双重校验锁测试
 *
 * @author 杨郑兴
 * @Date 2018/12/12 15:07
 * @官网 www.weifuwukt.com
 */
public class Single5Test {

    public static void main(String[] args) {

        /*
        双检锁/双重校验锁测试
        不合法的构造函数
        编译时错误：构造函数 Single5() 是不可见的
            Single5 single5 = new Single5();
        */
        Single5 single51 = Single5.getInstance();
        Single5 single52 = Single5.getInstance();
        System.out.println("双检锁/双重校验锁：" + (single51 == single52));

    }
}
