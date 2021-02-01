package com.code.demo.sync;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Date 2021/1/29 14:15
 * @Author LBWNB
 **/
public class Test {
    public static void main(String[] args) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put("1","1");
        HashMap hashMap = new HashMap();
        hashMap.put("1","1");

        LongAdder longAdder = new LongAdder()
    }
}
