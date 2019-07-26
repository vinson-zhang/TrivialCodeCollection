package com.zt.study.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * ZhangTao
 * 2019/7/26 21:35
 * Description:
 */
public class JdkDynamicProxy implements InvocationHandler {

    private Object object;

    public JdkDynamicProxy(Object object){
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("执行前......");
        method.invoke(object,args);
        System.out.println("执行后........");
        return null;
    }
}
