package log.proxy.logproxy;

import log.proxy.controller.HelloController;
import log.proxy.enums.LogProxyEnum;
import log.proxy.exception.LogProxyException;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author 杨郑兴
 * @Date 2018/12/12 23:29
 * @官网 www.weifuwukt.com
 */
public class HelloCtrollerLog implements MethodInterceptor {

    private static HelloCtrollerLog helloCtrollerLog = new HelloCtrollerLog();

    private Object target;

    private HelloCtrollerLog() {}

    public static HelloCtrollerLog getInstance(){
        return helloCtrollerLog;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("开启日志---");
        System.out.println(methodProxy);
        Object invoke = methodProxy.invoke(target, objects);
        System.out.println("日志打印完成---");
        return invoke;
    }

    private Object createInstance(Object object){
        this.target = object;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    public static void logPrint(Class c){
        try {
            Class<?> forName = Class.forName("log.proxy.controller.HelloController");
            HelloController helloController = (HelloController)forName.newInstance();
            HelloCtrollerLog helloCtrollerLog = HelloCtrollerLog.getInstance();
            HelloController instance = (HelloController)helloCtrollerLog.createInstance(helloController);
            instance.hello();
        } catch (Exception e) {
            throw new LogProxyException(c.toString()+LogProxyEnum.EXCUPTION.getMessage());
        }
    }

}
