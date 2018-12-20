package builder;

/**
 * @author 杨郑兴
 * @Date 2018/12/13 13:22
 * @官网 www.weifuwukt.com
 */
//构建美国人
public class ManBuilder implements PersionBuilder {

    private Persion persion;

    public ManBuilder() {
        this.persion = new Persion();
    }

    public void builderHead() {
        System.out.println("外国人头部  鼻子尖、长脸、蓝眼睛");
        persion.setHead("head外国人头部  鼻子尖、长脸、蓝眼睛");
    }

    public void builderBody() {
        System.out.println("外国人长得比较高，块头大");
        persion.setBody("body外国人长得比较高，块头大");
    }

    public void builderFoot() {
        System.out.println("外国人腿长");
        persion.setFoot("foot外国人腿长");
    }

    public Persion builderPersion() {
        return persion;
    }

}
