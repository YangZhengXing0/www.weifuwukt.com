package single;

/**
 * 懒汉式测试
 *
 * @author 杨郑兴
 * @Date 2018/12/12 14:57
 * @官网 www.weifuwukt.com
 */
public class Single2Test {

    public static void main(String[] args) {
        /*
        懒汉式测试
        不合法的构造函数
        编译时错误：构造函数 Single2() 是不可见的
        Single2 single2 = new Single2();
        */
        Single2 single21 = Single2.getInstance();
        Single2 single22 = Single2.getInstance();
        System.out.println("懒汉式：" + (single21 == single22));


    }
}
