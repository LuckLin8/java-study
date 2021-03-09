package com.code.demo.sync;

import com.code.demo.utils.ThreadPoolUtil;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Date 2021/2/5 15:20
 * @Author LBWNB
 **/
public class ThreadLocalDemo {

    public static void main(String[] args) throws InterruptedException {

        int threads = 3;
        CountDownLatch countDownLatch = new CountDownLatch(threads);
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtil.createThreadPoolByName(threads, threads, 10, "demo");
        InnerClass innerClass = new InnerClass();
        for(int i = 1; i <= threads; i++) {
            threadPoolExecutor.execute(()->{
                for(int j = 0; j < 4; j++) {
                    innerClass.add(String.valueOf(j));
                    innerClass.print();
                }
                innerClass.set("hello world");
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        threadPoolExecutor.shutdown();
    }

    private static class InnerClass {

        public void add(String newStr) {
            StringBuilder str = Counter.counter.get();
            Counter.counter.set(str.append(newStr));
        }

        public void print() {
            System.out.printf("Thread name:%s , ThreadLocal hashcode:%s, Instance hashcode:%s, Value:%s\n",
                    Thread.currentThread().getName(),
                    Counter.counter.hashCode(),
                    Counter.counter.get().hashCode(),
                    Counter.counter.get().toString());
        }

        public void set(String words) {
            Counter.counter.set(new StringBuilder(words));
            System.out.printf("Set, Thread name:%s , ThreadLocal hashcode:%s,  Instance hashcode:%s, Value:%s\n",
                    Thread.currentThread().getName(),
                    Counter.counter.hashCode(),
                    Counter.counter.get().hashCode(),
                    Counter.counter.get().toString());
        }
    }

    private static class Counter {

        private static ThreadLocal<StringBuilder> counter = ThreadLocal.withInitial(StringBuilder::new);

    }

}