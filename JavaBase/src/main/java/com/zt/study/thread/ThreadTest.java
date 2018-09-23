package com.zt.study.thread;

import org.junit.Test;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ Author: zxh
 * @ Date: Created in 15:40 2018/9/21
 * @ Description: 线程相关的测试
 * @ Modified By:
 */
public class ThreadTest {

    @Test
    public void executorTest(){


//        new ThreadFactoryBu

//        new ThreadPoolExecutor();

        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
//        threadPoolTaskExecutor.setRejectedExecutionHandler();
//        threadPoolTaskExecutor.setAllowCoreThreadTimeOut();
//        threadPoolTaskExecutor.setCorePoolSize();

        Executor executor = Executors .newFixedThreadPool(20, Executors.defaultThreadFactory());
    }


}
