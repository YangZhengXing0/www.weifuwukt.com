package single;

/**
 * @author 杨郑兴
 * @Date 2018/12/12 15:48
 * @官网 www.weifuwukt.com
 */
public class EnumTest {

    public static void main(String[] args) {

     /*   执行结果为:
        枚举有参构造方法code=200,messge=200
        枚举有参构造方法code=500,messge=500
        HTTP_200
        HTTP_200
        HTTP_200
        HTTP_500
        发现Enum1.HTTP_200构造函数只执行一次
        这就是单列模式中可以使用枚举模型的原因
        */
        System.out.println(Enum1.HTTP_200);
        System.out.println(Enum1.HTTP_200);
        System.out.println(Enum1.HTTP_200);
        System.out.println(Enum1.HTTP_500);

    }
}

