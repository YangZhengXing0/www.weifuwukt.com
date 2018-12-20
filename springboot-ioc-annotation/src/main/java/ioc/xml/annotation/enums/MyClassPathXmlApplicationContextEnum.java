package ioc.xml.annotation.enums;

/**
 * @author 杨郑兴
 * @Date 2018/12/17 17:49
 * @官网 www.weifuwukt.com
 */
public enum  MyClassPathXmlApplicationContextEnum {
    NOT_FOUND(1,"该包下，没有任何注解");

    private Integer code;
    private String  message;

    MyClassPathXmlApplicationContextEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
