package daily.test;

/**
 * Created by wb-zj373670 on 2018/8/14.
 */
public class ConClass {

    public String name;
    public ConClass(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
    public ConInterface newCon(){
        return new Con();
    }

    class Con implements ConInterface{
        @Override
        public String getValue() {
            return getName();
        }
    }
}
