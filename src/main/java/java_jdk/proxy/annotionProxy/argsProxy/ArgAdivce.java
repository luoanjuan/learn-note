package java_jdk.proxy.annotionProxy.argsProxy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by wb-zj373670 on 2018/8/14.
 */
@Component
@Aspect
public class ArgAdivce {
    @Around("@args(java_jdk.proxy.annotionProxy.argsProxy.AnnotationForPointcut)")
    public void around(ProceedingJoinPoint pjp){
        System.out.println("@args pointcut");
        try{
            pjp.proceed();
        }catch (Throwable e){

        }
    }
    @Around("args(java_jdk.proxy.annotionProxy.argsProxy.Para)")
    public void argsAround(ProceedingJoinPoint pjp){
        System.out.println("args pointcut");
        try{
            pjp.proceed();
        }catch (Throwable e){

        }
    }

}
