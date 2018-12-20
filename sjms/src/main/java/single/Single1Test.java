package single;

/**
 * 饿汉式测试
 *
 * @author 杨郑兴
 * @Date 2018/12/12 14:48
 * @官网 www.weifuwukt.com
 */
public class Single1Test {

    public static void main(String[] args) {
        /*
        饿汉式测试
        不合法的构造函数
        编译时错误：构造函数 Single1() 是不可见的
        Single1 single1 = new Single1();
        */
        Single1 single11 = Single1.getInstance();
        Single1 single12 = Single1.getInstance();
        System.out.println("饿汉式：" + (single11 == single12));

    }
}
