package com.summer.service;

import com.spring.Autowired;
import com.spring.BeanNameAware;
import com.spring.Component;
import com.spring.InitializingBean;

@Component
public class UserService implements BeanNameAware, InitializingBean {

    private String beanName;

    @Autowired
    private OrderService orderService;

    @Override
    public void setBeanName(String name) {
        beanName = name;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Initializing Bean...");
    }

    public void test() {
        System.out.println(orderService);
        System.out.println(beanName);
        try {
            afterPropertiesSet();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
