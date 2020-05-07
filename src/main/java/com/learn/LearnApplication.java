package com.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @description: spring boot
 * @author: zhoujuan
 * @create: 2020-04-16 10:51
 */
@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class LearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnApplication.class, args);
    }
}
