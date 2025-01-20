package com.spring.beans.factory.support;
import com.spring.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegister {
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
