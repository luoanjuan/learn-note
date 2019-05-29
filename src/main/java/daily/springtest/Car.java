package daily.springtest;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

/**
 * Created by wb-zj373670 on 2018/4/9.
 */
public class Car implements BeanFactoryAware,BeanNameAware,InitializingBean,DisposableBean
{

    private String color;
    private int maxSpeed;

    private BeanFactory beanFactory;
    private String beanName;
    private String brand;



    public String getColor() {
        return color;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setBrand(String brand){
        System.out.println("调用setBrand()设置属性");
        this.brand = brand;
    }

    public Car(){
        System.out.println("调用car的构造函数");
    }



     public void introduce(){
        System.out.println("brand: " + brand + ", color: " + color + ", maxSpeed: " + maxSpeed);
     }



    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("调用beanFactory的setBeanFactory方法");
        this.beanFactory = beanFactory;
    }


    public void setBeanName(String s) {
        System.out.println("调用BeanNameAware的setBeanName()方法");
        this.beanName = s;
    }


    public void destroy() throws Exception {
        System.out.println("调用InitializingBean的destory()方法");
    }


    public void afterPropertiesSet() throws Exception {
        System.out.println("调用InitializingBean的afterPropertiesSet()方法");
    }

    public void myInit(){
         System.out.println("调用bean的init-method()方法");
    }

    public void myDestroy(){
        System.out.println("调用bean的destroy-method()方法");
    }


}
