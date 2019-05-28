package daily.proxy.proxy1;

/**
 * Created by wb-zj373670 on 2018/5/3.
 */
public class Female implements Human{

    @Override
    public void show(String string){
        System.out.println("female" + "------------" + string);
    }
}
