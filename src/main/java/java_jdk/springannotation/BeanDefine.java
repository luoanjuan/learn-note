package java_jdk.springannotation;

/**
 * Created by wb-zj373670 on 2018/5/2.
 */
public class BeanDefine {
    private String id;
    private String className;

    public BeanDefine(String id, String className){
        this.id = id;
        this.className = className;
    }

    public String getId() {
        return id;
    }

    public String getClassName() {
        return className;
    }
}
