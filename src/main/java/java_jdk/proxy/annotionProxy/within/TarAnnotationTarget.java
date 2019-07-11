package java_jdk.proxy.annotionProxy.within;

import org.springframework.stereotype.Component;

/**
 * Created by wb-zj373670 on 2018/8/14.
 */
@WithinPointCut
@Component
public interface TarAnnotationTarget {

    public void testOne();
}
