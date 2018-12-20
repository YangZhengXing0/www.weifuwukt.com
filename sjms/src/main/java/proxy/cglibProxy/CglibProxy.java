package proxy.cglibProxy;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib动态代理
 *
 * @author 杨郑兴
 * @Date 2018/12/12 21:10
 * @官网 www.weifuwukt.com
 */
public class CglibProxy implements MethodInterceptor {

    private Object targetObject;

    public CglibProxy(Object targetObject) {
        this.targetObject = targetObject;
    }

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib代理 开启事务----");
        Object invoke = methodProxy.invoke(targetObject, objects);
        System.out.println("cglib代理 提交事务----");
        return invoke;
    }

    // 这里的目标类型为Object，则可以接受任意一种参数作为被代理类，实现了动态代理
    public Object getInstance() {
        // 设置需要创建子类的类
        Enhancer enhancer = new Enhancer();
        // 设置需要代理的对象,代理生成target的子类
        enhancer.setSuperclass(targetObject.getClass());
        //  设置代理人
        // 或者enhancer.setCallback(this);
        enhancer.setCallback(new CglibProxy(targetObject));//这里一定要使用new CglibProxy(targetObject)，不能使用new CglibProxy()
        return enhancer.create();
    }

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        CglibProxy cglibProxy = new CglibProxy(userDao);
        UserDao cglibProxyInstance = (UserDao) cglibProxy.getInstance();
        cglibProxyInstance.add();
    }
}
