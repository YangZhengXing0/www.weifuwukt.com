package springmvc.servlet;

import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.util.StringUtils;
import springmvc.annotation.MyController;
import springmvc.annotation.MyRequestMapping;
import springmvc.utils.MyClassUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 杨郑兴
 * @Date 2018/12/18 16:37
 * @官网 www.weifuwukt.com
 */
//自定义前端控制器DispatcherServlet
public class DispatcherServlet extends HttpServlet {

    //SpringMvc 容器对象  key:类名id   value:对象
    private ConcurrentHashMap<String,Object> springMvcBeans = new ConcurrentHashMap<String, Object>();
    //SpringMvc 容器对象  key:请求地址   value:类
    private ConcurrentHashMap<String,Object> urlFoundBeans = new ConcurrentHashMap<String, Object>();
    //SpringMvc 容器对象  key:请求地址   value:方法名
    private ConcurrentHashMap<String,String> urlFoundMethods = new ConcurrentHashMap<String, String>();

    @Override
    public void init() throws ServletException {
        //1.获取当前包下的所有类
        List<Class<?>> classList = MyClassUtil.getClasses("springmvc.controller");
        //2.判断类上是否加注解@MyController,有则注入到springmvc容器里，存放在map集合中，key：默认类小写，value：对象
        findClassMvcAnnotation(classList);
        //3.将来url映射和方法进行关联
        handlerMapping();
    }

    //2.判断类上是否加注解@MyController,有则注入到springmvc容器里，存放在map集合中，key：默认类小写，value：对象
    public void findClassMvcAnnotation(List<Class<?>> classList){

        for (Class<?> classInfo:classList){
            MyController myControllerAnnotation = classInfo.getDeclaredAnnotation(MyController.class);
            if(!StringUtils.isEmpty(myControllerAnnotation)){
                //默认类名首字母小写
                String className = toLowerCaseFirstOne(classInfo.getSimpleName());
                Object newInstance = newInstance(classInfo);
                springMvcBeans.put(className,newInstance);
            }
        }
    }

    //2.1 把首字母变成小写
    public String toLowerCaseFirstOne(String className){
        //如果首字母就是小写，返回字符串
        if(Character.isLowerCase(className.charAt(0))){
            return className;
        }else{
            //如果首字母就不是小写，则先获取第一个字符转换成小写，然后与截取第一个字符之外的字符拼接，返回拼接后的字符穿
            return (new StringBuilder()).append(Character.toLowerCase(className.charAt(0))).append(className.substring(1)).toString();
        }
    }

    //2.2 根据反射，实列对象
    public Object newInstance(Class<?> classInfo){
        try {
            return classInfo.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    //3.将来url映射和方法进行关联
    public void handlerMapping(){
        //3.1 获取springmvc bean容器对象
        Set<Map.Entry<String, Object>> entrys = this.springMvcBeans.entrySet();
        for(Map.Entry<String,Object> entry:entrys){
            Object bean = entry.getValue();
            //3.2 遍历springmvc bean容器，判断类上是否存在url映射的注解
            Class<?> beanClass = bean.getClass();
            MyRequestMapping myRequestMapping = beanClass.getAnnotation(MyRequestMapping.class);
            String beanUrl = null;
            if(!StringUtils.isEmpty(myRequestMapping)){
               //3.2.1 获取类上的url地址
                beanUrl = myRequestMapping.value();
            }
            //3.3 遍历类的所有方法，判断方法上是否有url映射的注解
            Method[] beandMethods = beanClass.getDeclaredMethods();
            for(Method method:beandMethods){
                MyRequestMapping methodmyRequestMapping = method.getDeclaredAnnotation(MyRequestMapping.class);
                if(!StringUtils.isEmpty(methodmyRequestMapping)){
                    //3.4 将来url映射和方法进行关联，添加到urlFoundBeans容器中
                    String  methodUrl = beanUrl+methodmyRequestMapping.value();
                    urlFoundBeans.put(methodUrl,bean);
                    urlFoundMethods.put(methodUrl,method.getName());
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //4.1 处理请求url地址
        String requestURI = request.getRequestURI();
        if(StringUtils.isEmpty(request)){
            return;
        }
        //4.2 从map集合中获取控制对象
        Object object = urlFoundBeans.get(requestURI);
        if(StringUtils.isEmpty(object)){
            response.getWriter().println("404 not found this page of"+requestURI);
            return;
        }
        //4.3 使用url地址获取方法
        String methodName = urlFoundMethods.get(requestURI);
        if(StringUtils.isEmpty(methodName)){
            response.getWriter().println("not found method of " +requestURI);
            return;
        }
        //4.4 使用java的反射机制调用方法
        //4.5 使用java的反射机制获取方法返回结果
        Object invoke = methodInvoke(object, methodName);
        response.getWriter().println(invoke.toString());
        //4.6 调用试图转换器渲染给页面展示
        myResourceViewResolver(invoke.toString(),request,response);
    }

    //视图解析
    public void myResourceViewResolver(String pageName,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        //根路径
        String prefix = "/";
        String suffix = ".jsp";
        request.getRequestDispatcher(prefix+pageName+suffix).forward(request,response);
    }

    private  Object methodInvoke(Object object,String methodName){
        try {
            Class<?> objectClass = object.getClass();
            Method method = objectClass.getMethod(methodName);
            Object result = method.invoke(object);
            return result;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

}
