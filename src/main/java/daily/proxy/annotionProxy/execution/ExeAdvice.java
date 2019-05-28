package daily.proxy.annotionProxy.execution;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by wb-zj373670 on 2018/8/14.
 *
 */
@Component
@Aspect
public class ExeAdvice {

//    @Around("execution(* daily.proxy.annotionProxy..*(..))")
//    public void around(ProceedingJoinPoint joinPoint){
//        System.out.println("ExeAdivice");
//        try{
//            joinPoint.proceed();
//        }catch (Throwable e){
//            e.printStackTrace();
//        }
//    }

    @Around("execution(* test*(Object))")
    public void classPlus(ProceedingJoinPoint joinPoint){
        System.out.println("Example Class+");
        try{
            joinPoint.proceed();
        }catch (Throwable e){
            e.printStackTrace();
        }
    }
}
