package daily.proxy.proxy1;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by wb-zj373670 on 2018/5/3.
 */
public class Male implements Human{

    public Male(){
        System.out.println("male constructor");
    }

    @PostConstruct
    public void init(){
        System.out.println("male @PostConstruct");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("destory");
    }

    public void show(String string){
        System.out.println("male" + "---------" + string);
    }
}
