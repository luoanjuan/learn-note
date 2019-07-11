package java_jdk.springannotation;

/**
 * Created by wb-zj373670 on 2018/5/2.
 */
public class User1DaoImpl {
    String name;

    public void setName(String name) {
        this.name = name;
    }

    public void show(){
        System.out.println("这是Dao1的方法" + name);
    }
}
