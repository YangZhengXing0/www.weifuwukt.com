package builder;

/**
 * @author 杨郑兴
 * @Date 2018/12/13 13:27
 * @官网 www.weifuwukt.com
 */
//构建日本人
public class JpBuilder implements PersionBuilder {
    public void builderHead() {
        System.out.println("日本人头部 圆脸");
    }

    public void builderBody() {
        System.out.println("日本人 比较矮");
    }

    public void builderFoot() {
        System.out.println("日本人  腿短");
    }

    public Persion builderPersion() {
        return null;
    }
}
