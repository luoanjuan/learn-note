package java_jdk.MethodHandle;

import java.lang.invoke.*;

/**
 * Created by wb-zj373670 on 2018/4/22.
 */
public class InvokeDynamicTest {

    class GrandFather{
        void thinking(){
            System.out.println("i am grandfather");
        }
    }

    class Father extends GrandFather{
        void thinking(){
            System.out.println("i am father");
        }
    }

    class Son extends Father{
        void thinking(){
            try{
                MethodType mt = MethodType.methodType(void.class);
                MethodHandle mh = MethodHandles.lookup().findSpecial(GrandFather.class, "thinking", mt, getClass());
                mh.invoke(this);
            }catch (Throwable e){
                //
            }
        }
    }

    public static void main(String[] args){
        (new InvokeDynamicTest().new Son()).thinking();
    }

    }
