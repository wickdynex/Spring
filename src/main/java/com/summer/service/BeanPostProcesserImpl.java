package com.summer.service;

import com.spring.BeanPostProcesser;
import com.spring.Component;

@Component
public class BeanPostProcesserImpl implements BeanPostProcesser {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return null;
    }
}
