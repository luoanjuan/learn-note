package daily.proxy.simpleproxy;

import java.lang.reflect.Proxy;

/**
 * Created by wb-zj373670 on 2018/4/28.
 */
public class Client {
    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject();
        DynamicProxy dynamicProxy = new DynamicProxy(realSubject);
        Subject subject =(Subject) Proxy.newProxyInstance(dynamicProxy.getClass().getClassLoader(),
                realSubject.getClass().getInterfaces(),
                dynamicProxy);

        subject.rent();
    }
}
