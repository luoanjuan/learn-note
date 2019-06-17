import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

public class TestMain {

    public static int HH = 0;

//    static sun.misc.Unsafe unsafe = sun.misc.Unsafe.getUnsafe();

    public static void main(String[] args) {
        try {
            ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
            Resource resource = applicationContext.getResource( "classpath:/context.xml");
            applicationContext.refresh();

            DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getBeanFactory();
            XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
            xmlBeanDefinitionReader.loadBeanDefinitions(resource);
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
