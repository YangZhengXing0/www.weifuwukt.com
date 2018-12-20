package single;

/**
 * 静态内部类测试
 *
 * @author 杨郑兴
 * @Date 2018/12/12 15:15
 * @官网 www.weifuwukt.com
 */
public class Single3Test {

    public static void main(String[] args) {
        /*
        静态内部类测试
        不合法的构造函数
        编译时错误：构造函数 Single3() 是不可见的
        Single3 single3 = new Single3();
        */
        Single3 single31 = Single3.getInstance();
        Single3 single32 = Single3.getInstance();
        System.out.println(" 静态内部类:" + (single31 == single32));

    }
}
