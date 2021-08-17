package com.example.algorithm.dynamicProgramming.zeroOneKnapsack;

/*
Given a set of non-negative integers, and a value sum, determine if there is a subset of the given set with sum equal to
given sum
 */
public class SubsetSumProblem {

    public static void main(final String[] s) {
        final SubsetSumProblem p = new SubsetSumProblem();
        final int[] items = {2, 3, 5, 7, 8, 10};
        System.out.println(p.subsetSumProblemWithRecursion(items, items.length, 11));
        System.out.println(p.subsetSumProblemWithTopDown(items, 6));
    }

    public boolean subsetSumProblemWithRecursion(final int[] items, final int index, final int sum) {
        if (sum == 0) {
            return true;
        }
        if (index == 0) {
            return false;
        }
        if (items[index - 1] <= sum) {
            return subsetSumProblemWithRecursion(items, index - 1, sum - items[index - 1]) ||
                   subsetSumProblemWithRecursion(items, index - 1, sum);
        } else {
            return subsetSumProblemWithRecursion(items, index - 1, sum);
        }
    }

    public boolean subsetSumProblemWithTopDown(final int[] items, final int sum) {
        if (sum == 0) {
            return true;
        }
        if (items.length == 0) {
            return false;
        }

        final boolean[][] dp = new boolean[items.length + 1][sum + 1];
        for (int i = 0; i < items.length + 1; i++) {
            dp[i][0] = true;
        }
        for (int j = 1; j < sum + 1; j++) {
            dp[0][j] = false;
        }

        for (int i = 1; i < items.length + 1; i++) {
            for (int j = 1; j < sum + 1; j++) {
                if (items[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j - items[i - 1]] || dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[items.length][sum];
    }
}
