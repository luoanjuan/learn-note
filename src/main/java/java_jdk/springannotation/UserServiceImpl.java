package java_jdk.springannotation;

/**
 * Created by wb-zj373670 on 2018/5/2.
 */
public class UserServiceImpl {
    public UserDaoImpl userDao;
    public User1DaoImpl user1Dao;

    @ZxfResource
    public  User2DaoImpl user2Dao;

    @ZxfResource(name="userDao")
    public void setUserDao(UserDaoImpl userDao){
        this.userDao = userDao;
    }

    @ZxfResource
    public void setUser1Dao(User1DaoImpl user1Dao){
        this.user1Dao = user1Dao;
    }

    public void show(){
        userDao.show();
        user1Dao.show();
        user2Dao.show();
        System.out.println("这是service的方法");
    }
}
