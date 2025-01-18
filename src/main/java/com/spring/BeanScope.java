package com.spring;

public enum BeanScope {
    singleton("singleton"),
    prototype("prototype");

    private final String scope;

    BeanScope(String scope) {
        this.scope = scope;
    }

    public String getScope() {
        return scope;
    }
}