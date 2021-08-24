package com.example.algorithm.dynamicProgramming.random;

import java.util.HashMap;
import java.util.Map;

public class FibonacciNumber {

    private final Map<Integer, Integer> dp = new HashMap<>();

    public static void main(String[] s) {
        FibonacciNumber f = new FibonacciNumber();
        System.out.println(f.getFibonacciNumber(30));
    }

    public int getFibonacciNumber(int n) {
        if (n == 0) {
            dp.put(0, 0);
            return 0;
        }
        if (n == 1) {
            dp.put(1, 1);
            return 1;
        }
        if (dp.containsKey(n)) {
            return dp.get(n);
        }
        int p = getFibonacciNumber(n - 1);
        dp.put(n - 1, p);
        int q = getFibonacciNumber(n - 2);
        dp.put(n - 2, q);
        dp.put(n, p + q);
        return p + q;
    }

}
