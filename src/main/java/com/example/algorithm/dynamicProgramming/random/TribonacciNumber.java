package com.example.algorithm.dynamicProgramming.random;

import java.util.HashMap;
import java.util.Map;

/*
The Tribonacci sequence Tn is defined as follows:
T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.
Given n, return the value of Tn
*
Input: n = 4
Output: 4
Explanation:
T_3 = 0 + 1 + 1 = 2
T_4 = 1 + 1 + 2 = 4
 */
public class TribonacciNumber {

    private final Map<Integer, Integer> dp = new HashMap<>();

    public static void main(String[] s) {
        TribonacciNumber t = new TribonacciNumber();
        System.out.println(t.tribonacci(25));
    }

    public int tribonacci(int n) {
        if (n == 0) {
            dp.put(n, 0);
            return 0;
        } else if (n == 1 || n == 2) {
            dp.put(n, 1);
            return 1;
        }
        if (dp.containsKey(n)) {
            return dp.get(n);
        }
        int p = tribonacci(n - 3);
        dp.put(n - 3, p);
        int q = tribonacci(n - 2);
        dp.put(n - 2, q);
        int r = tribonacci(n - 1);
        dp.put(n - 1, r);
        dp.put(n, p + q + r);
        return p + q + r;
    }
}
