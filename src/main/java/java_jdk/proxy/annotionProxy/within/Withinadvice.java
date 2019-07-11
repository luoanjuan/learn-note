package java_jdk.proxy.annotionProxy.within;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by wb-zj373670 on 2018/8/14.
 */
@Component
@Aspect
public class Withinadvice {

    @Around("within(java_jdk.proxy.annotionProxy.within.WithinTarget)")
    public void around(ProceedingJoinPoint pjp){
        System.out.println("within pointcut");
        try{
            pjp.proceed();
        }catch (Throwable e){
            e.printStackTrace();
        }
    }

    @Around("@target(java_jdk.proxy.annotionProxy.within.TargetPointCut)")
    public void targetAnnotation(ProceedingJoinPoint pjp){
        System.out.println("@target pointCut");
        try{
            pjp.proceed();
        }catch (Throwable e){
            e.printStackTrace();
        }
    }

    @Around("@within(java_jdk.proxy.annotionProxy.within.WithinPointCut)")
    public void withinAnnotation(ProceedingJoinPoint pjp){
        System.out.println("@within pointCut");
        try{
            pjp.proceed();
        }catch (Throwable e){
            e.printStackTrace();
        }
    }
}
