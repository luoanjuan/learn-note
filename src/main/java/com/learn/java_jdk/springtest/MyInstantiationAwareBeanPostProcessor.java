package com.learn.java_jdk.springtest;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

import java.beans.PropertyDescriptor;

/**
 * Created by wb-zj373670 on 2018/4/9.
 */
public class MyInstantiationAwareBeanPostProcessor  extends InstantiationAwareBeanPostProcessorAdapter {
    @Override
    //实例化bean前调用
    public Object postProcessBeforeInstantiation(Class<?> aClass, String s) throws BeansException {
        if("car".equals(s)){
            System.out.println("InstantiationAwareBeanPostProcessor   postProcessBeforeInstantiation()");
        }

        return null;
    }

    @Override
    //实例化bean后调用
    public boolean postProcessAfterInstantiation(Object o, String s) throws BeansException {
        if("car".equals(s)){
            System.out.println("InstantiationAwareBeanPostProcessor postProcessAfterInstantiation()");
        }
        return true;
    }

    @Override
    //设置某个属性时候调用
    public PropertyValues postProcessPropertyValues(PropertyValues propertyValues, PropertyDescriptor[] propertyDescriptors, Object o, String s) throws BeansException {
        if("car".equals(s)){
            System.out.println("InstantiationAwareBeanPostProcessor postProcessPropertyValues");
        }
        return propertyValues;
    }

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        return o;
    }
}
