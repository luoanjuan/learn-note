package com.learn.java_jdk.springtest;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Created by wb-zj373670 on 2018/4/9.
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(beanName.equals("car")){
            Car car = (Car)bean;
            if(car.getColor() == null){
                System.out.println("调用BeanPostProcessor的postProcessBeforeInitialization()方法，color属性为空，设置为黑");
            }
        }
        return bean;
    }


    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(beanName.equals("car")){
            System.out.println("调用beanPostProcessor的postProcessAfterInitialization()方法，设置maxSpeed");
            Car car = (Car)bean;
            car.setMaxSpeed(200);
        }
        return bean;
    }
}
