package builder;

/**
 * @author 杨郑兴
 * @Date 2018/12/13 13:33
 * @官网 www.weifuwukt.com
 */
//构建人物，整合所有部件
public class PersonDirector {
    public Persion createPerson(PersionBuilder persionBuilder){
        persionBuilder.builderHead();;
        persionBuilder.builderBody();
        persionBuilder.builderFoot();
        Persion persion = persionBuilder.builderPersion();
        return persion;
    }

    public static void main(String[] args) {
        PersonDirector personDirector = new PersonDirector();
        Persion person = personDirector.createPerson(new ManBuilder());
        System.out.println(person.getHead());
        System.out.println(person.getBody());
        System.out.println(person.getFoot());
    }
}
