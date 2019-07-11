package java_jdk.proxy.annotionProxy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wb-zj373670 on 2018/7/17.
 */
public class MainTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("resources/java_jdk/proxy/proxy.xml");
//        TargetTestInterface Example = (TargetTestInterface)applicationContext.getBean("targetTest");
//        Para para = new Para();
//        ParaOne one = new ParaOne();
//        ParaTwo two = new ParaTwo();
//        Example.methodTwo(one);
//        Example.methodTwo(two);
        TargetTestSub test = (TargetTestSub)applicationContext.getBean("targetTestSub");
        test.methodOne();
//        Example.testTwo(two);
        System.out.println("---------------");

    }
}
