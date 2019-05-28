package uaf;

/**
 * Created by wb-zj373670 on 2018/7/16.
 */
public class Fun1 {
    Biz create(Biz biz){
        return new Biz() {
            @Override
            public void apply() {
                System.out.println("11before");
                biz.apply();
                System.out.println("11after");
            }
        };

    }
}
