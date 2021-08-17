package com.example.algorithm.dynamicProgramming.zeroOneKnapsack;

/*
Given weights and values of n items, put these items in a knapsack of 'capacity' to get the maximum total value in the
knapsack.
In other words, given two integer arrays value[0..n-1] and weight[0..n-1] which represent values and weights
associated with n items respectively. Also given an integer 'capacity' which represents knapsack capacity, find out the
maximum value subset of value[] such that sum of the weights of this subset is smaller than or equal to 'capacity'.
You cannot break an item, either pick the complete item or don’t pick it (0-1 property).
 */
public class ZeroOneKnapsack {

    public static void main(final String[] s) {
        final ZeroOneKnapsack z = new ZeroOneKnapsack();
        final int[] value = {60, 100, 120};
        final int[] weight = {10, 20, 30};
        System.out.println(z.knapsackWithTopDown(value, weight, 3, 50));
    }

    public int knapsackWithRecursion(final int[] value, final int[] weight, final int index, final int capacity) {
        if (capacity == 0 || index == 0) {
            return 0;
        }
        if (weight[index - 1] <= capacity) {
            return Math
                .max(value[index - 1] + knapsackWithRecursion(value, weight, index - 1, capacity - weight[index - 1]),
                     knapsackWithRecursion(value, weight, index - 1, capacity));
        } else {
            return knapsackWithRecursion(value, weight, index - 1, capacity);
        }
    }

    /*
    Initially the 2-D matrix 'dp' is fully initialized with -1
     */
    public int knapsackWithMemoization(final int[] value, final int[] weight, final int index, final int capacity,
                                       final int[][] dp) {
        if (capacity == 0 || index == 0) {
            return 0;
        }
        if (dp[index][capacity] != -1) {
            return dp[index][capacity];
        }

        if (weight[index - 1] <= capacity) {
            dp[index][capacity] = Math
                .max(value[index - 1] + knapsackWithMemoization(value, weight, index - 1, capacity - weight[index - 1],
                                                                dp),
                     knapsackWithMemoization(value, weight, index - 1, capacity, dp));
        } else {
            dp[index][capacity] = knapsackWithMemoization(value, weight, index - 1, capacity, dp);
        }
        return dp[index][capacity];
    }

    public int knapsackWithTopDown(final int[] value, final int[] weight, final int index, final int capacity) {
        final int[][] dp = new int[index + 1][capacity + 1];
        for (int i = 0; i < index + 1; i++) {
            for (int j = 0; j < capacity + 1; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                }
            }
        }

        for (int i = 1; i < index + 1; i++) {
            for (int j = 1; j < capacity + 1; j++) {
                if (weight[i - 1] <= j) {
                    dp[i][j] = Math.max(value[i - 1] + dp[i - 1][j - weight[i - 1]], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[index][capacity];

    }
}
