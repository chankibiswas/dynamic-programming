package com.example.algorithm.dynamicProgramming.zeroOneKnapsack;

/*
Sum of subset differences
Given a set of integers, the task is to divide it into two sets S1 and S2 such that the absolute difference between their
sums is minimum.
If there is a set S with n elements, then if we assume Subset1 has m elements, Subset2 must have n-m elements and the
value of abs(sum(Subset1) – sum(Subset2)) should be minimum.

Example:
Input:  arr[] = {1, 6, 11, 5}
Output: 1
Explanation:
Subset1 = {1, 5, 6}, sum of Subset1 = 12
Subset2 = {11}, sum of Subset2 = 11
 */
public class MinimumSubsetDifference {

    public static void main(final String[] s) {
        final int arr[] = {1, 6, 11, 5};
        System.out.println(new MinimumSubsetDifference().calculateMinimumSubsetSumDifference(arr));
    }

    public int calculateMinimumSubsetSumDifference(final int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum = sum + arr[i];
        }
        // Get Subset sum matrix
        final boolean[][] dp = getSubsetSumMatrix(arr, sum);

        // Now find minimum difference by iterating over all possible subset sums, i.e. where dp[arr.length][j] is true
        // Math.abs(sum - 2*j) gives difference full array's sum and 2 subset sums.
        int minimumDiff = sum;
        for (int j = 0; j < sum + 1; j++) {
            if (dp[arr.length][j]) {
                minimumDiff = Math.min(minimumDiff, Math.abs(sum - (2 * j)));
            }
        }
        return minimumDiff;
    }

    private boolean[][] getSubsetSumMatrix(final int[] arr, final int sum) {
        final boolean[][] dp = new boolean[arr.length + 1][sum + 1];
        for (int i = 0; i < arr.length + 1; i++) {
            dp[i][0] = true;
        }
        for (int j = 1; j < sum + 1; j++) {
            dp[0][j] = false;
        }
        for (int i = 1; i < arr.length + 1; i++) {
            for (int j = 1; j < sum + 1; j++) {
                if (arr[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j - arr[i - 1]] || dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp;
    }
}
