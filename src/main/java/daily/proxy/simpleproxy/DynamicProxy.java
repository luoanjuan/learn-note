package daily.proxy.simpleproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by wb-zj373670 on 2018/4/28.
 */
public class DynamicProxy implements InvocationHandler {

    private Object subject;
    public DynamicProxy(Object object){
        this.subject = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before rent ");
//        method.invoke(subject,args);
        System.out.println("After rent");
        return null;
    }
}
