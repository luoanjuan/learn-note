package daily.proxy.proxy1;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wb-zj373670 on 2018/5/3.
 */
public class Test {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("resources/daily/proxy/proxy.xml");
        Human human = (Human)context.getBean("human");
//        Human daily.proxy = (Human)Proxy.newProxyInstance(Human.class.getClassLoader(), new Class[] { Human.class }, new ServiceProxy());
//        human.show("female");
//        human.show("male");
        Male ma = new Male();

    }
}
