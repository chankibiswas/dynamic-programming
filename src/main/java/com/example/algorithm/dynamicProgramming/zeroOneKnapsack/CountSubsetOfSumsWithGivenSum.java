package com.example.algorithm.dynamicProgramming.zeroOneKnapsack;

/*
Count of subsets sum with a Given sum
Given an array arr[] of length N and an integer X, the task is to find the number of subsets with sum equal to X.
Example:

Input: arr[] = {1, 2, 3, 3}, X = 6
Output: 3
All the possible subsets are {1, 2, 3},
{1, 2, 3} and {3, 3}
 */
public class CountSubsetOfSumsWithGivenSum {

    public static void main(final String[] s) {
        final int[] arr = {1, 2, 3, 3};
        final int x = 6;
        System.out.println(new CountSubsetOfSumsWithGivenSum().countSubsetOfSumsWithGivenSum(arr, x));
    }

    public int countSubsetOfSumsWithGivenSum(final int[] arr, final int x) {
        final int[][] dp = new int[arr.length + 1][x + 1];
        for (int i = 0; i < arr.length + 1; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; j < x + 1; j++) {
            dp[0][j] = 0;
        }
        for (int i = 1; i < arr.length + 1; i++) {
            for (int j = 1; j < x + 1; j++) {
                if (arr[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j - arr[i - 1]] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[arr.length][x];
    }
}
