package java_jdk.proxy.annotionProxy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by wb-zj373670 on 2018/7/17.
 */
@Component("adviceTest")
@Aspect
public class AdivceTest {

   @Pointcut("execution(* java_jdk.proxy.annotionProxy.TargetTest.*(..))")
    public void pointCut(){    }

    @Around("execution(* java_jdk.proxy.annotionProxy.TargetTestInterface.methodOne(..))")
    public void around1(ProceedingJoinPoint pjp){
       System.out.println(pjp.toLongString());
       System.out.println("around1");
       try{
           pjp.proceed();
       }catch (Throwable e){

       }
    }

    @Around("target(java_jdk.proxy.annotionProxy.TargetTestSub)")
    public void around2(ProceedingJoinPoint pjp){
        System.out.println("around2");
        try{
            pjp.proceed();
        }catch (Throwable e){

        }
    }

//    @Around("@annotation(Example))")
//    public void around2(ProceedingJoinPoint pjp, AnnoTest Example){
//        System.out.println(pjp.toLongString());
//        System.out.println("around2");
//    }

//    @Around("target(java_jdk.proxy.annotionProxy.TargetTest) && args(parameter)")
//    public void AroundAnnotation(final ProceedingJoinPoint pjp, Parameter parameter){
//        System.out.println("AroundAnnotation");
//        try{
//            pjp.proceed();
//        }catch (Throwable e){
//        }
//    }
}
