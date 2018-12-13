package com.demo.spring.factory;


import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPath;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassPathXmlApplicationContext implements BeanFactory {
    //容器的核心，用来存放注入的bean
    private Map<String, Object> container = new HashMap<>();

    //解析xml文件，通过反射将配置的bean放到containerz中
    public ClassPathXmlApplicationContext(String fileName) throws Exception {
        SAXBuilder sb = new SAXBuilder();
        Document doc  = sb.build(this.getClass().getClassLoader().getResourceAsStream(fileName));
        Element root = doc.getRootElement();
        List list = XPath.selectNodes(root, "/beans/bean");

        //扫描配置文件中的bean
        for (int i = 0; i < list.size(); i++) {
            Element bean = (Element) list.get(i);
            String id = bean.getAttributeValue("id");
            String clazz = bean.getAttributeValue("class");
            Object o = Class.forName(clazz).newInstance();
            container.put(id, o);
        }
    }
    @Override
    public Object getBean(String id) {
        return container.get(id);
    }
}
