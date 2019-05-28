/**
 * Created by wb-zj373670 on 2019/1/11.
 */
public class Person {

    private String name;

    Person(String name){
        this.name = name;
    }

    Person(){
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "name:" + name;
    }
}
