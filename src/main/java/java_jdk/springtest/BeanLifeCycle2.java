package java_jdk.springtest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wb-zj373670 on 2018/4/22.
 */
public class BeanLifeCycle2 {
    public static void main(String[] args){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("resources/spring.xml");
        TestA car1 = (TestA)applicationContext.getBean("testA");
        car1.testB();
    }


}
