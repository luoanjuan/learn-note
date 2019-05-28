package daily.cglib;

/**
 * Created by wb-zj373670 on 2018/5/9.
 */
public class Test {

    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        UserManager userManager = (UserManager)proxy.getProxy(UserManager.class);
        userManager.function();
    }
}
