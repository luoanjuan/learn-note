import java.util.function.Consumer;

public class TestTwo{

    private int age;

    private String name;

    TestTwo(String aa, int age){
        this.name = aa;
        this.age = age;
    }


    public void getName(){
        System.out.println(name) ;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void getAge(){
        System.out.println(age);
    }

    public static void main(String[] args) {

    }
    
}
