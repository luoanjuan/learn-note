package java_jdk.proxy.annotionProxy;

import org.springframework.stereotype.Component;

/**
 * Created by wb-zj373670 on 2018/8/15.
 */
@Component
public class TargetTestSub extends TargetTest {
    @Override
    protected void methodTwo(ParameterTwo parameter) {
        System.out.println("method in TargetTestSub");
    }
}
