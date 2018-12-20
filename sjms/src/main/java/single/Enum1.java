package single;

/**
 * @author 杨郑兴
 * @Date 2018/12/12 15:48
 * @官网 www.weifuwukt.com
 */
public enum Enum1 {
    HTTP_200(200, "请求成功"),
    HTTP_500(500, "请求失败");

    private Integer code;
    private String messge;

    Enum1(Integer code, String messge) {
        System.out.println("枚举有参构造方法code=" + code + ",messge=" + code);
        this.code = code;
        this.messge = messge;
    }

    private Enum1() {
        System.out.println("枚举无参构造方法");
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessge() {
        return messge;
    }

    public void setMessge(String messge) {
        this.messge = messge;
    }


}
