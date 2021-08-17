package com.example.algorithm.dynamicProgramming.unboundedKnapsack;

/*
Unbounded Knapsack (Repetition of items allowed)
Given a knapsack weight W and a set of n items with certain value and weight, we need to calculate minimum
amount that could make up this quantity exactly.
This is different from classical Knapsack problem, here we are allowed
to use unlimited number  of instances of an item.
Examples:

Input : W = 100
       val[]  = {1, 30}
       wt[] = {1, 50}
Output : 100
There are many ways to fill knapsack.
1) 2 instances of 50 unit weight item.
2) 100 instances of 1 unit weight item.
3) 1 instance of 50 unit weight item and 50
   instances of 1 unit weight items.

So calculating the maximum value, we will get 100 from 2 method, i.e. 100 instances of 1 unit weight
 */
public class UnboundedKnapsack {

    public static void main(final String[] s) {
        final UnboundedKnapsack z = new UnboundedKnapsack();
        final int[] value = {1, 30};
        final int[] weight = {1, 50};
        System.out.println(z.knapsack(value, weight, 100));
    }

    private int knapsack(final int[] value, final int[] weight, final int w) {
        if (w == 0) {
            return 0;
        }
        if (value.length == 0) {
            return 0;
        }
        final int[][] dp = new int[value.length + 1][w + 1];
        for (int i = 0; i < value.length + 1; i++) {
            dp[i][0] = 0;
        }
        for (int j = 1; j < w + 1; j++) {
            dp[0][j] = 0;
        }
        for (int i = 1; i < value.length + 1; i++) {
            for (int j = 1; j < w + 1; j++) {
                if (weight[i - 1] <= j) {
                    dp[i][j] = Math.max(value[i - 1] + dp[i][j - weight[i - 1]], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[value.length][w];
    }

}
