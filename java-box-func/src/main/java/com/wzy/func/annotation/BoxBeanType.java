package com.wzy.func.annotation;

public enum BoxBeanType {

    // 每次new一个对象
    NEW_OBJECT(1),

    // 单例模式载入
    SINGLE_OBJECT(2);

    int type=0;

    BoxBeanType(int i) {
        this.type = i;
    }
}
