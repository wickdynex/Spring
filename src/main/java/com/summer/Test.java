package com.summer;

import com.spring.ApplicationContext;
import com.summer.service.UserService;

public class Test {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ApplicationContext(ApplicationConfig.class);

        UserService userService = (UserService) applicationContext.getBean("userService");
        System.out.println(userService);
        userService.test();
    }
}
