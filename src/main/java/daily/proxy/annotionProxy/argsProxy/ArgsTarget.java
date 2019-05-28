package daily.proxy.annotionProxy.argsProxy;

import org.springframework.stereotype.Component;

/**
 * Created by wb-zj373670 on 2018/8/14.
 */
@Component
public class ArgsTarget {

    public void testOne(ParaOne para){
        System.out.println(para.name);
    }

}
