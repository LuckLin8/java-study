package com.code.demo.sync;

import com.code.demo.utils.ThreadPoolUtil;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Date 2021/1/29 14:15
 * @Author LBWNB
 **/
public class Test {


    /**
     * 两数和为定值返回下标
     **/
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i< nums.length; i++) {
            if(map.containsKey(target - nums[i])) {
                return new int[] {map.get(target-nums[i]),i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 两个正序数组返回中位数
     **/
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int left = (n + m + 1) / 2;
        int right = (n + m + 2) / 2;
        //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
        return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;
    }

    private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
        if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);
        if (len1 == 0) return nums2[start2 + k - 1];

        if (k == 1) return Math.min(nums1[start1], nums2[start2]);

        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;

        if (nums1[i] > nums2[j]) {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        }
        else {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }

    public static void main(String[] args) {
        Test test = new Test();
        ThreadPoolExecutor executor = ThreadPoolUtil.createThreadPoolByName(17, 1000, 1000, "测试同步方法线程池");
        executor.execute(()-> {
            try {
                test.testA();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executor.execute(()-> {
            try {
                test.testB();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
    ReentrantLock lock = new ReentrantLock();
    public void testA() throws InterruptedException {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"进入A方法");
            TimeUnit.SECONDS.sleep(3);
            testB();
        }finally {
            lock.unlock();
        }
    }

    public void testB() throws InterruptedException {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "进入B方法");
            TimeUnit.SECONDS.sleep(3);
            System.out.println(Thread.currentThread().getName() + "退出B方法");
        }finally {
            lock.unlock();
        }
    }

}
