package com.demo.spring.factory;

public class Test {
    public static void main(String[] args) throws Exception{
        //加载配置文件
        BeanFactory f = new ClassPathXmlApplicationContext("mybean.xml");

        Team oe = (Team) f.getBean("E");
        oe.say();

        Team os = (Team) f.getBean("S");
        os.say();
    }
}
