package proxy.jdkProxy;

import proxy.jdkProxy.impl.IuUserDaoImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 杨郑兴
 * @Date 2018/12/12 20:12
 * @官网 www.weifuwukt.com
 */
// 每次生成动态代理类对象时,实现了InvocationHandler接口的调用处理器对象
public class InvocationHandlerImpl implements InvocationHandler {

    private Object target;// 这其实业务实现类对象，用来调用具体的业务方法

    public InvocationHandlerImpl(Object target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        System.out.println("调用开始处理---");
        result = method.invoke(target, args);
        System.out.println("调用结束处理---");
        return result;
    }

    public static void main(String[] args) throws Exception {
        IuUserDao iuUserDao = new IuUserDaoImpl();
        InvocationHandlerImpl invocationHandlerImpl = new InvocationHandlerImpl(iuUserDao);
        //方式一
//        ClassLoader classLoader = iuUserDao.getClass().getClassLoader();
//        Class<?>[] interfaces = iuUserDao.getClass().getInterfaces();
//        IuUserDao newProxyInstance = (IuUserDao)Proxy.newProxyInstance(classLoader, interfaces, invocationHandlerImpl);
//        newProxyInstance.add();
        //方式二
        IuUserDao newProxyInstance = (IuUserDao) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{IuUserDao.class}, invocationHandlerImpl);
        newProxyInstance.add();

    }
}
