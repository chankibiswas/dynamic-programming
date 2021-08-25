package com.example.algorithm.dynamicProgramming.stairs;

import java.util.HashMap;
import java.util.Map;

public class ClimbingStairs {

    private final Map<Integer, Integer> dp = new HashMap<>();

    public static void main(String[] s) {
        ClimbingStairs c = new ClimbingStairs();
        System.out.println(c.climbStairs(3));
    }

    public int climbStairs(int n) {
        if (n == 0) {
            dp.put(0, 0);
            return 0;
        }
        if (n == 1) {
            dp.put(1, 1);
            return 1;
        }
        if (n == 2) {
            dp.put(2, 2);
            return 2;
        }
        if (dp.containsKey(n)) {
            return dp.get(n);
        }

        int p = climbStairs(n - 2);
        dp.put(n - 2, p);
        int q = climbStairs(n - 1);
        dp.put(n - 1, q);
        dp.put(n, p + q);
        return p + q;
    }
}
