package com.spring.beans.factory.support;

import com.spring.BeansException;
import com.spring.beans.factory.config.BeanDefinition;

public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition) throws BeansException;
}
