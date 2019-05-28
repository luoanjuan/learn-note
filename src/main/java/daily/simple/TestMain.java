package daily.simple;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by wb-zj373670 on 2018/4/14.
 */
public class TestMain {
    public static void main(String[] arg) throws Exception{
        ZhangSan zhang = new ZhangSan();
        InvocationHandler handler = new Invoker(zhang);
        PersonOne zhangSan = (PersonOne)Proxy.newProxyInstance(handler.getClass().getClassLoader(), zhang.getClass().getInterfaces(), handler);
        zhangSan.test();
        zhangSan.toString();
    }
}

interface PersonOne {
    public void test();
}

class ZhangSan implements PersonOne{
    public void test(){
        System.out.println("Hello");
    }
}

class Invoker implements InvocationHandler{
   private Object personOne;
    public Invoker(Object person){
        this.personOne = person;
    }
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable{
        System.out.println(method.getDeclaringClass());
        System.out.println(Object.class);
        method.invoke(personOne,args);
        return null;
    }
}