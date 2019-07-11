package java_jdk.proxy.proxy1;

import org.springframework.beans.factory.FactoryBean;

import javax.annotation.PostConstruct;
import java.lang.reflect.Proxy;

/**
 * Created by wb-zj373670 on 2018/5/3.
 */
public class ServiceProviderBean implements FactoryBean {
    private Object proxy;
    private Class className;

    public ServiceProviderBean(){
        System.out.println("ServiceProviderBean constructor");
    }

    public void setClassName(Class aClass) {
        this.className = aClass;
    }

    @PostConstruct
    public void init(){
        System.out.println("ServiceProviderBean @PostConstruct");
        boolean bool = this.className == Human.class;
        System.out.println(bool);
        proxy = Proxy.newProxyInstance(this.className.getClassLoader(), new Class[] { this.className }, new ServiceProxy());
//        java_jdk.proxy = Proxy.newProxyInstance(Human.class.getClassLoader(), new Class[] { Human.class }, new ServiceProxy());
    }

    @Override
    public Object getObject() throws Exception {
//        Human java_jdk.proxy = (Human)Proxy.newProxyInstance(Human.class.getClassLoader(), new Class[] { Human.class }, new ServiceProxy());
        System.out.println("ServiceProviderBean getObject");
        return proxy;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

}
