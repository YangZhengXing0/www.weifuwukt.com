package ioc.xml.dom4j;


import ioc.xml.exception.MyClassPathXmlApplicationContextException;
import ioc.xml.utils.MyClassPathXmlApplicationContextUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.util.List;

/**
 * @author 杨郑兴
 * @Date 2018/12/17 14:29
 * @官网 www.weifuwukt.com
 */
public class MyClassPathXmlApplicationContext {

    private String xmlpath;

    public MyClassPathXmlApplicationContext(String xmlpath) {
        this.xmlpath = xmlpath;
    }

    public Object getBean(String beanId) throws Exception{
        //判断beanId是否为空
        if(StringUtils.isEmpty(beanId)){
            throw new MyClassPathXmlApplicationContextException(MyClassPathXmlApplicationContextUtil.NOT_FOUND_BEANID.getMessage());
        }
        //1.解析xml，获取所有bean节点信息
        List<Element> elements = parseXml();
        //判断配置文件中是否配置了bean
        if(elements.size()<=0){
            throw new MyClassPathXmlApplicationContextException(MyClassPathXmlApplicationContextUtil.NOT_BEAN.getMessage());
        }
        //2.使用方法参数beanId查找配置文件中bean节点的id信息是否一致。并返回class地址
        String className = findByElementClass(elements, beanId);
        //3.通过反射机制，初始化对象
        if(StringUtils.isEmpty(className)){
            throw new MyClassPathXmlApplicationContextException(MyClassPathXmlApplicationContextUtil.NOT_CLASS.getMessage());
        }
        Object newInstance = initBean(className);
        return newInstance;
    }

    //使用方法参数beanId查找配置文件中bean节点的id信息是否一致。并返回class地址
    public String findByElementClass(List<Element> elements,String beanId){
        for(Element element:elements){
            //获取当前节点id属性值
            String xmlBeanId = element.attributeValue("id");
            //判断此节点是否有id属性值
            if(StringUtils.isEmpty(xmlBeanId)){
                throw new MyClassPathXmlApplicationContextException(MyClassPathXmlApplicationContextUtil.NOT_ID.getMessage());
            }
            if(xmlBeanId.equals(beanId)){
                //获取class值
                String xmlClassValue = element.attributeValue("class");
                return xmlClassValue;
            }
        }
        return null;
    }
    //通过反射初始化bean
    public Object initBean(String className) throws Exception{
        Class<?> forName = Class.forName(className);
        Object newInstance = forName.newInstance();
        return newInstance;
    }
    //解析xml
    public List<Element> parseXml() throws Exception{
        SAXReader saxReader = new SAXReader();

        Document document = saxReader.read(getResourceAsStream(this.xmlpath));
        //获取根节点
        Element rootElement = document.getRootElement();
        //获取根节点下的所有字子节点
        List<Element> elements = rootElement.elements();
        return elements;
    }
    //解析每个节点
    public void readNote(){

    }
    //获取当前上下文路径
    public InputStream getResourceAsStream(String xmlpath){
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(xmlpath);
        return inputStream;
    }

}
