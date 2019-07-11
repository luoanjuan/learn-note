package java_jdk.proxy.annotionProxy.argsProxy;

import org.springframework.stereotype.Component;

/**
 * Created by wb-zj373670 on 2018/8/14.
 */
@Component
public class TargetWithArgs {

    public void testOne(Para para, Para para1){
        System.out.println(para.name);
    }

    public void testTwo(ParaTwo two){
        System.out.println(two.name);
    }
}
