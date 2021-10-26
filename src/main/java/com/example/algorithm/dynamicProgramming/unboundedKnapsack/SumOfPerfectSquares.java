package com.example.algorithm.dynamicProgramming.unboundedKnapsack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given an integer n, return the least number of perfect square numbers that sum to n.

A perfect square is an integer that is the square of an integer; in other words, it is the product of some integer with
itself. For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.
*
Input: n = 12
Output: 3
Explanation: 12 = 4 + 4 + 4.
*
Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.
 */
public class SumOfPerfectSquares {

    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n);
        dp[0] = 0;

        int count = 1;
        int sq = 1;
        List<Integer> nums = new ArrayList<>();
        while (sq <= n) {
            nums.add(sq);
            dp[sq] = 1;
            count++;
            sq = count * count;
        }

        for (int i : nums) {
            for (int j = 2; j <= n; j++) {
                if (j > i) {
                    dp[j] = Math.min(dp[j], 1 + dp[j - i]);
                }
            }
        }
        return dp[n];
    }
}
