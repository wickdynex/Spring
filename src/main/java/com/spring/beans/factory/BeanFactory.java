package com.spring.beans.factory;

import com.spring.BeansException;

public interface BeanFactory {

    public Object getBean(String name) throws BeansException;
}
