package single;

import com.sun.org.apache.regexp.internal.RE;

/**
 * 枚举方式
 *
 * @author 杨郑兴
 * @Date 2018/12/12 15:21
 * @官网 www.weifuwukt.com
 */
public class Single4 {
    ;

    private Single4() {
    }

    public static Single4 getInstance() {
        return EnumSingle4.INSTANCE.getInstance();
    }

    private enum EnumSingle4 {

        INSTANCE;
        private Single4 single4;

        private EnumSingle4() {
            single4 = new Single4();
        }

        public Single4 getInstance() {
            return single4;
        }
    }
}

