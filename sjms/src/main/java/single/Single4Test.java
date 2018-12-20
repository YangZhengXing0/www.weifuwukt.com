package single;

/**
 * 枚举测试
 *
 * @author 杨郑兴
 * @Date 2018/12/12 15:42
 * @官网 www.weifuwukt.com
 */
public class Single4Test {
    public static void main(String[] args) {

        Single4 single41 = Single4.getInstance();
        Single4 single42 = Single4.getInstance();
        System.out.println("枚举:" + (single41 == single42));

    }
}
