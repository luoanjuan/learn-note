package java_jdk.proxy.annotionProxy;

/**
 * Created by wb-zj373670 on 2018/7/17.
 */

public abstract class TargetTest implements TargetTestInterface {

    @Override
    @AnnoTest
    public void methodOne(){
        System.out.println("testmethod methodOne()");
        TargetTest.this.methodTwo(new ParameterTwo());
    }


    protected void methodTwo(ParameterTwo parameter) {
        System.out.println("method in abstract");
    }

    @Override
    public void methodTwo(ParameterOne parameter) {
        System.out.println(parameter.name);
    }
}
