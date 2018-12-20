package single;

/**
 * 单列模式：饿汉式
 *
 * @author 杨郑兴
 * @Date 2018/12/12 14:19
 * @官网 www.weifuwukt.com
 */
public class Single1 {

    private static Single1 single1 = new Single1();

    //让构造函数为 private，这样该类就不会被实例化,其他地方就不能new了
    private Single1() {
    }

    public static Single1 getInstance() {
        return single1;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

