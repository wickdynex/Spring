package com.spring;

public class BeanDefinition {
    private String beanScope;
    private Class beanClass;

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public String getBeanScope() {
        return beanScope;
    }

    public void setBeanScope(String beanScope) {
        this.beanScope = beanScope;
    }
}
