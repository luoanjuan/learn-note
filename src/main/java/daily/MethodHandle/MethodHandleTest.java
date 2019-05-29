package daily.MethodHandle;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * Created by wb-zj373670 on 2018/4/22.
 */
public class MethodHandleTest {
    static class ClassA{
        public void println(String s){
            System.out.println(s);
        }
    }

    public static void main(String[] args) throws  Throwable{
        Object obj = System.currentTimeMillis() % 2 == 0? System.out : new ClassA();
        getPrintlnMH(obj).invokeExact("hhhh");
    }

    private static MethodHandle getPrintlnMH(Object reviever) throws  Throwable{
        //MethodType 表示方法类型，第一个参数是方法返回值，余下参数是方法的具体参数
        MethodType methodType = MethodType.methodType(void.class, String.class);
        //在指定的类中查找符合给定方法名称、方法类型且符合调用权限的方法句柄
        return MethodHandles.lookup().findVirtual(reviever.getClass(), "println", methodType).bindTo(reviever);
    }
}
