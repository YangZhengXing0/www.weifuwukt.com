package single;

/**
 * 单列模式：静态内部类
 *
 * @author 杨郑兴
 * @Date 2018/12/12 14:19
 * @官网 www.weifuwukt.com
 */
public class Single3 {

    //让构造函数为 private，这样该类就不会被实例化,其他地方就不能new了
    private Single3() {
    }

    public static final Single3 getInstance() {
        return Single3Class.single3;
    }

    private static class Single3Class {
        private static final Single3 single3 = new Single3();
    }
}


