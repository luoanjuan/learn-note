package daily.springtest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wb-zj373670 on 2018/6/26.
 */
@Configuration
public class AnnotationTest {

    public AnnotationTest(){
        System.out.println("annotation");
    }

    @Bean(
            name={"testA"}
    )
   public TestA testA(){return new TestA();}
}
