import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import java.util.*;
import java.util.concurrent.locks.LockSupport;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class TestMain {

    public static int HH = 0;

//    static sun.misc.Unsafe unsafe = sun.misc.Unsafe.getUnsafe();

    public static void main(String[] args) {
        Object object = new Object();
        try {
            Thread thread = new Thread(()->{
                try {
                    synchronized (object){
                        System.out.println("sa");
                        object.wait();
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }


            });
            thread.start();

            System.out.println(thread.isInterrupted());
            thread.interrupt();
            System.out.println(thread.isInterrupted());
            System.out.println("park");
            System.out.println("interrupt");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void testOne(String aa){
        System.out.println(aa);
    }

    public static boolean test(String aa){
        return true;
    }

    public static void change(TestTwo testTwo){
       testTwo.setAge(2);
    }

}
