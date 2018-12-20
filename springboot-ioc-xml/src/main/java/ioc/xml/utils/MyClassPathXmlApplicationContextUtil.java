package ioc.xml.utils;

/**
 * @author 杨郑兴
 * @Date 2018/12/17 14:50
 * @官网 www.weifuwukt.com
 */
public enum  MyClassPathXmlApplicationContextUtil {
    NOT_FOUND_BEANID(0,"beanId不能为空"),
    NOT_BEAN(1,"配置文件中没有配置bean"),
    NOT_CLASS(2,"配置文件中没有配置class属性值"),
    NOT_ID(3,"bean中没有配置id属性值")
    ;
    private Integer code;
    private String  message;

    MyClassPathXmlApplicationContextUtil(Integer code, String message) {
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
