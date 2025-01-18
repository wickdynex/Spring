package com.summer;

import com.spring.ApplicationContext;

public class Test {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ApplicationContext(ApplicationConfig.class);

        Object userService = applicationContext.getBean("userService");
        System.out.println(userService);
        System.out.println(userService);
        System.out.println(userService);
    }
}
