package com.code.demo.sync;

import com.code.demo.utils.ThreadPoolUtil;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Date 2021/1/29 14:01
 * @Author LBWNB
 **/
public class SyncMethodDemo {

    private static int i = 1;

    public static synchronized void increase() {
        i++;
    }

    public void unsafeIncrease() {
        i++;
    }

    public static void main(String[] args) throws InterruptedException {
        //cpu密集
        ThreadPoolExecutor executor = ThreadPoolUtil.createThreadPoolByName(17, 1000, 1000, "测试同步方法线程池");
        SyncMethodDemo syncMethodDemo = new SyncMethodDemo();
        executor.execute(()->{
            for (int i1 = 1; i1 < 10000; i1++) {
                SyncMethodDemo.increase();
            }
        });
        SyncMethodDemo syncMethodDemo2 = new SyncMethodDemo();
        executor.execute(()->{
            for (int i1 = 1; i1 < 10000; i1++) {
                SyncMethodDemo.increase();
            }
        });
        executor.shutdown();
        TimeUnit.SECONDS.sleep(10);
        System.out.println(SyncMethodDemo.i);
    }
}
