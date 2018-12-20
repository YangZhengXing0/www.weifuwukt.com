package jdbc.pool.exception;

/**
 * @author 杨郑兴
 * @Date 2018/12/19 15:55
 * @官网 www.weifuwukt.com
 */
public class IConnectionPoolException extends RuntimeException {
    public IConnectionPoolException(String message) {
        super(message);
    }
}
