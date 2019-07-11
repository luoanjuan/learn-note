package java_jdk.proxy.proxy1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by wb-zj373670 on 2018/5/3.
 */
public class ServiceProxy  implements InvocationHandler{
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(args[0] != null && args[0] instanceof String){
            String arg = (String)args[0];
            if("male".equals(arg)){
                Male male = new Male();
                method.invoke(male, args);
            }else if("female".equals(arg)){
                Female female = new Female();
                method.invoke(female,args);
            }
        }
        return proxy;
    }
}
