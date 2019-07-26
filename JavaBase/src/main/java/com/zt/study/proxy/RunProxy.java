package com.zt.study.proxy;

/**
 * ZhangTao
 * 2019/7/26 21:31
 * Description:
 */
public class RunProxy implements RunService {

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
