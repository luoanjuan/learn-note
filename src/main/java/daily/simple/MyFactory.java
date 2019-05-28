package daily.simple;

import org.springframework.beans.factory.FactoryBean;

/**
 * Created by wb-zj373670 on 2018/4/14.
 */
public class MyFactory implements FactoryBean{
    public Object getObject() throws Exception {
        return new String("hhh");
    }

    public Class<?> getObjectType() {
        return Person.class;
    }

    public boolean isSingleton() {
        return false;
    }

    class Person{
        private String name;
        private String add;

        public String toString(){
            return name + "  " + add;
        }
    }
}
