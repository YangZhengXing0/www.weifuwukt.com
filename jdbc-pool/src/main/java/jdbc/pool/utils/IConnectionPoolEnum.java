package jdbc.pool.utils;

/**
 * @author 杨郑兴
 * @Date 2018/12/19 15:56
 * @官网 www.weifuwukt.com
 */
public enum IConnectionPoolEnum {
    NOT_FOUND_DBBEAN(1,"配置类为空")
    ;

    IConnectionPoolEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
