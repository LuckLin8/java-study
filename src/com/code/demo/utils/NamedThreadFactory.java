package com.code.demo.utils;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Date 2021/1/19 14:20
 * @Author LBWNB
 * 自定义名称线程工厂
 **/
public class NamedThreadFactory implements ThreadFactory {
    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private  String namePrefix;
    private final ThreadGroup group;

    public NamedThreadFactory(String name ) {
        this.namePrefix = name + "-" + poolNumber.getAndIncrement() + "-thread-";
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
        if (t.isDaemon()){t.setDaemon(false);}
        if (t.getPriority() != Thread.NORM_PRIORITY){t.setPriority(Thread.NORM_PRIORITY);}
        return t;
    }
}
