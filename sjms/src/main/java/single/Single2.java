package single;

/**
 * 单列模式：懒汉模式
 *
 * @author 杨郑兴
 * @Date 2018/12/12 14:19
 * @官网 www.weifuwukt.com
 */
public class Single2 {

    private static Single2 single2;

    //让构造函数为 private，这样该类就不会被实例化,其他地方就不能new了
    private Single2() {
    }

    public static Single2 getInstance() {
        if (single2 == null) {
            Single2 single2 = new Single2();
        }
        return single2;
    }
}

