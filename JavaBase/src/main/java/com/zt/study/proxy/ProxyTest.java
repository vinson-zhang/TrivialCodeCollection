package com.zt.study.proxy;

import org.junit.Test;

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


    /**
     * 静态代理测试
     */
    interface RunService{
        void run();
    }

    /**
     * 实现类
     */
    class RunImpl implements RunService{

        @Override
        public void run() {
            System.out.println("跑步..............");
        }
    }

    /**
     * 代理类
     */
    class RunProxy implements RunService{

        private RunService runService;

        public RunProxy(RunService runService){
            this.runService = runService;
        }

        @Override
        public void run() {
            System.out.println("跑步前......");
            runService.run();
            System.out.println("跑步后...........");
        }
    }


    @Test
   public void staticProxyTest(){
        RunImpl run = new RunImpl();
        run.run();

        RunProxy runProxy = new RunProxy(run);
        runProxy.run();
    }




}
