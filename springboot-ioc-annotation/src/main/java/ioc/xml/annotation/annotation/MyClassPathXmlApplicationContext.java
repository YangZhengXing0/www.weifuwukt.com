package ioc.xml.annotation.annotation;

import ioc.xml.annotation.MyClassPathXmlApplicationContextException;
import ioc.xml.annotation.enums.MyClassPathXmlApplicationContextEnum;
import ioc.xml.annotation.utils.MyClassUtil;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 杨郑兴
 * @Date 2018/12/17 17:24
 * @官网 www.weifuwukt.com
 */
//手写sprin ioc 注解版
public class MyClassPathXmlApplicationContext {
    //扫包的范围
    private String packageName;
    //spring ioc容器
    private static ConcurrentHashMap<String,Object> beans = null;

    public MyClassPathXmlApplicationContext(String packageName) throws Exception {
        this.packageName = packageName;
        beans = new ConcurrentHashMap<String,Object>();
        initBeans();
        initEntryField();
    }

    //初始化对象
    public void initBeans() throws Exception{
        //1.使用java的反射机制扫包，获取当前包下的所有类
        List<Class<?>> classList = MyClassUtil.getClasses(packageName);
        //2.判断类上是否存在注入bean的主注解（MyService）
        ConcurrentHashMap<String, Object> classExisAnnotation = findClassMyServiceAnotation(classList);
        //判断该包下是否有注解
        if(StringUtils.isEmpty(classExisAnnotation)){
            throw new MyClassPathXmlApplicationContextException(MyClassPathXmlApplicationContextEnum.NOT_FOUND.getMessage());
        }
    }

    //初始化属性
    public void initEntryField() throws Exception{
        //遍历所有bean容器对象
        for(Map.Entry<String,Object> entry:beans.entrySet()){
            //判断属性上面是否有标注注解
//            Object bean = entry.getKey();
            Object value = entry.getValue();
            attriAssign(value);
        }
    }

    public Object getBean(String beanId) throws Exception{
        if(StringUtils.isEmpty(beanId)){
            throw new Exception("beanId参数不能为空");
        }
        Object classInfo = beans.get(beanId);
//        if(StringUtils.isEmpty(classInfo)){
//            throw new Exception("class not found");
//        }
        return classInfo;
    }

    public Object newInstance(Class<?> classInfo) throws Exception{
        return classInfo.newInstance();
    }

    //
    public ConcurrentHashMap<String,Object> findClassMyServiceAnotation(List<Class<?>> classList)throws Exception{
        for(Class<?> classInfo:classList){
            //判断类上是否有注解
            MyService annotation = classInfo.getAnnotation(MyService.class);
            if(!StringUtils.isEmpty(annotation)){
                //获取类名
                String className = classInfo.getSimpleName();
                //beanId 类名小写
                String beanId = toLowerCaseFirstOne(className);
                Object newInstance = newInstance(classInfo);
                beans.put(beanId,newInstance);
                continue;
            }
        }
        return beans;
    }
    //首字母转小写
    public static String toLowerCaseFirstOne(String s){
        if(Character.isLowerCase(s.charAt(0))){
            return s;
        }else{
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    //依赖注入
    public void attriAssign(Object o) throws Exception{
        Class<? extends Object> classInfo = o.getClass();
        //使用反射机制，获取当前类的所有属性
        Field[] declaredFields = classInfo.getDeclaredFields();
        //判断当前类中属性上是否标注有注解(@MyResource)
        for(Field field:declaredFields){
            System.out.println(field.getName());
            MyResource annotation = field.getAnnotation(MyResource.class);
            if(!StringUtils.isEmpty(annotation)){
                //获取属性名称
                String fieldName = field.getName();
                Object bean = beans.get(fieldName);
             if(!StringUtils.isEmpty(bean)){
                    //允许访问私有属性
                    field.setAccessible(true);
                    //为当前对现象属性赋值，object表示当前对象，bean表示赋的值
                    field.set(o,bean);
                }
            }
        }
    }
}
