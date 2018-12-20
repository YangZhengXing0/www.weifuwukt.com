package builder;

/**
 * @author 杨郑兴
 * @Date 2018/12/13 13:20
 * @官网 www.weifuwukt.com
 */
public interface PersionBuilder {

    public void builderHead();
    public void builderBody();
    public void builderFoot();

    //组装
    Persion builderPersion();
}
