package com.code.demo.utils;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Date 2021/1/29 14:06
 * @Author LBWNB
 **/
public class ThreadPoolUtil {

    /**
     * 指定核心线程，最大线程，阻塞队列最大值，以及线程池的名称
     * 拒绝策略为：调用线程执行
     **/
    public static ThreadPoolExecutor createThreadPoolByName(int corePoolSize, int maximumPoolSize, int blockingQueueSize, String threadPoolName) {
        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(blockingQueueSize), new NamedThreadFactory(threadPoolName), new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
