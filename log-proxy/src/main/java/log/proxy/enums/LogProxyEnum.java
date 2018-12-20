package log.proxy.enums;

/**
 * @author 杨郑兴
 * @Date 2018/12/12 23:52
 * @官网 www.weifuwukt.com
 */
public enum LogProxyEnum {
    NOT_FOUND("类没有找到"),
    EXCUPTION("LogProxy日志抛出异常");
    private String message;

    LogProxyEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
