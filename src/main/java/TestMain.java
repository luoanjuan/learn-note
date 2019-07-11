import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TestMain {

    public static int HH = 0;

//    static sun.misc.Unsafe unsafe = sun.misc.Unsafe.getUnsafe();

    public static void main(String[] args) {
        try {
            System.out.println(new Random().nextLong());
//            List<Integer> hotelIdList = new ArrayList();
//            List<Integer> currentList = new ArrayList<>(10);
//
//            for(int i = 0; i < 92 ; i++){
//                hotelIdList.add(i);
//            }
//            for(int i = 0; i < hotelIdList.size(); i += 10){
//                int length = i+10 < hotelIdList.size() ? 10 : hotelIdList.size()-i;
//                currentList = hotelIdList.subList(i, i+length);
//                System.out.println(JSON.toJSONString(currentList));
//            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
