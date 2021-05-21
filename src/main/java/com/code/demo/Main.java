package com.code.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {

    private List<byte []> byteList = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        /**
         * 查看jvm启动默认内存大小
         * 本机16G
         * Memory                    used    total    max     usage    GC
         * heap                      53M     190M     622M    8.66%    gc.ps_scavenge.count          3
         * ps_eden_space             26M     63M      212M    12.70%   gc.ps_scavenge.time(ms)       42
         * ps_survivor_space         10M     10M      10M     99.82%   gc.ps_marksweep.count         1
         * ps_old_gen                16M     116M     467M    3.53%    gc.ps_marksweep.time(ms)      48
         * nonheap                   45M     47M      -1      97.19%
         * code_cache                9M      9M       240M    3.78%
         * metaspace                 32M     33M      -1      97.73%
         * compressed_class_space    3M      4M       1024M   0.37%
         */
        TimeUnit.SECONDS.sleep(30);
        System.out.println("----------------休眠结束--------------");
        Main main = new Main();
        for (int i = 0; i < 100; i++) {
            byte[] byteArray = new byte[2*1024*1024];//5mb
            System.out.println("----------------第"+(i+1)+"次--------------");
            TimeUnit.SECONDS.sleep(10);
            main.byteList.add(byteArray);
        }
        System.out.println(main.byteList.size());
    }
}
