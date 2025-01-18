package com.summer.service;

import com.spring.Autowired;
import com.spring.Component;

@Component
public class UserService {

    @Autowired
    private OrderService orderService;

    public void test() {
        System.out.println(orderService);
    }
}
