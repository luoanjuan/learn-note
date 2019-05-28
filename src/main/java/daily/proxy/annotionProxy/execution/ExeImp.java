package daily.proxy.annotionProxy.execution;

import org.springframework.stereotype.Component;

/**
 * Created by wb-zj373670 on 2018/8/14.
 */
@Component
public class ExeImp extends ExeInterface {
    @Override
    public void testOne() {
        System.out.println("interface method");
    }

    public void testTwo(){
        System.out.println("subClass method");
    }

    public void testThree(Object string){
        System.out.println(string);
    }
}
