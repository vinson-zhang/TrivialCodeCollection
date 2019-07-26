package com.zt.study.proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * ZhangTao
 * 2019/7/26 22:26
 * Description:
 */
public class CglibProxyInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("执行前.........");

        Object o1 = methodProxy.invokeSuper(o, objects);

        System.out.println("执行后..........");
        return o1;
    }
}
