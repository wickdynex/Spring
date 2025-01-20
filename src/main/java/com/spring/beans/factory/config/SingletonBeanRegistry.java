package com.spring.beans.factory.config;

public interface SingletonBeanRegistry {
    Object getSingleton(String beanName);
}
