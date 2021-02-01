package com.code.demo.sync;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date 2021/1/29 14:15
 * @Author LBWNB
 **/
public class Test {
    public static void main(String[] args) {
    }
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
}
