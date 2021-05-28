package com.code.demo;

import java.util.Arrays;
import java.util.Stack;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Date 2021/3/17 9:53
 * @Author LBWNB
 **/
public class Solution {
    public String longestCommonPrefix(String[] strs) {
        String tempString = "";
        for (String str : strs) {
            if ("".equals(str)) {
                return "";
            }
            if (null == tempString || "".equals(tempString) || str.equals(tempString)) {
                tempString = str;
                continue;
            }
            String temp = "";
            for (int i = 0; i < (Math.min(str.length(), tempString.length())); i++) {
                if (str.substring(i, i + 1).equals(tempString.substring(i, i + 1))) {
                    temp += str.substring(i, i + 1);
                } else {
                    if ("".equals(temp)) {
                        return temp;
                    }
                    break;
                }
            }
            tempString = temp;
        }
        return tempString;
    }

    public boolean isValid(String s) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            String temp = s.substring(i, i + 1);
            if ("(".equals(temp)) {
                stack.push(")");
            } else if ("[".equals(temp)) {
                stack.push("]");
            } else if ("{".equals(temp)) {
                stack.push("}");
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                if (!stack.pop().equals(temp)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static long count = 0L;

    public static void main(String[] args) {
//        Solution solution = new Solution();
//        String [] strings = {"caa","","a","acb"};
//        System.out.println(solution.longestCommonPrefix(strings));
//
//        solution.isValid("()");
//        System.out.println(climbStairs(45));
//        System.out.println(count);
        int i = 0;
    }

    public static int climbStairs(int n) {
        count++;
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else {
            return climbStairs(n - 1) + climbStairs(n - 2);
        }
    }

}
