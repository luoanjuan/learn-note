package java_jdk.proxy.simpleproxy;

/**
 * Created by wb-zj373670 on 2018/4/28.
 */
public class RealSubject  implements Subject{

    @Override
    public void rent() {
        System.out.println("I want rent a house.");
    }

    @Override
    public void hello(String str) {
        System.out.println("Hello " + str);
    }
}
