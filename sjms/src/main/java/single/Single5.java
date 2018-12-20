package single;

/**
 * 单列模式：双检锁/双重校验锁，官网推荐使用volatile,volatile使多线程可见性，禁止重排序，但是没有证原子性
 *
 * @author 杨郑兴
 * @Date 2018/12/12 14:19
 * @官网 www.weifuwukt.com
 */
public class Single5 {

    private static volatile Single5 single5;

    //让构造函数为 private，这样该类就不会被实例化,其他地方就不能new了
    private Single5() {
    }

    public static Single5 getInstance() {
        if (single5 == null) {
            synchronized (Single5.class) {
                if (single5 == null) {
                    single5 = new Single5();
                }
            }
        }
        return single5;
    }
}

