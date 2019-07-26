package com.zt.study.proxy;

import net.sf.cglib.proxy.Enhancer;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * ZhangTao
 * 2019/7/21 14:27
 * Description: 动态代理 与 静态代理
 */
public class ProxyTest {

    /**
     * 代理分为动态代理 和 静态代理
     * 静态代理： 既在编译的时候，就把实现类 和 代理类，全部写好
     * 动态代理： 即在 运行时的时候，根据需要动态的创建代理类和其实例，来完成某些功能
     *
     */

    @Test
   public void staticProxyTest(){
        RunImpl run = new RunImpl();
        run.run();

        RunProxy runProxy = new RunProxy(run);
        runProxy.run();
    }


    public static void main(String[] args) {
        RunImpl run = new RunImpl();
        InvocationHandler invocationHandler = new JdkDynamicProxy(run);

        RunService runService = (RunService) Proxy.newProxyInstance(run.getClass().getClassLoader(), new Class[]{RunService.class}, invocationHandler);

        runService.run();
    }

    @Test
    public void jdkDynamicProxy(){

        RunImpl run = new RunImpl();
        InvocationHandler invocationHandler = new JdkDynamicProxy(run);

        RunService runService = (RunService) Proxy.newProxyInstance(run.getClass().getClassLoader(), new Class[]{RunService.class}, invocationHandler);

        runService.run();
    }


    @Test
    public void cglibProxyTest(){
        // 字节码增强器
        Enhancer enhancer = new Enhancer();
        // 设置被代理类为父类
        enhancer.setSuperclass(User.class);
        // 设置回调
        enhancer.setCallback(new CglibProxyInterceptor());

        User user = (User) enhancer.create();
        user.eat();


    }




}
