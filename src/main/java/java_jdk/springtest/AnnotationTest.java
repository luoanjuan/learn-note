package java_jdk.springtest;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by wb-zj373670 on 2018/6/26.
 */
@Aspect
@Component
public class AnnotationTest {


    public AnnotationTest(){
    System.out.println("annotation");
    }

    @Before("execution(* java_jdk.springtest.TestA..*(..))")
    public void testA(){
        System.out.println("before");
    }
}
