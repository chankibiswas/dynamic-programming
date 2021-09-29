package com.example.algorithm.dynamicProgramming.unboundedKnapsack;

/*
Coin Change Problem to give minimum numbers of coins
Given a value V, if we want to make change for V cents, and we have infinite supply of each of C = { C1, C2, .. , Cm}
valued coins, what is the minimum number of coins to make the change?

Example:
Input: coins[] = {25, 10, 5}, V = 30
Output: Minimum 2 coins required
We can use one coin of 25 cents and one of 5 cents
 */
public class CoinChangeProblem2 {

    public static void main(final String[] s) {
        final int[] coin = {9, 6, 5, 1};
        final int v = 11;
        System.out.println(new CoinChangeProblem2().minimumCoinChangeToGiveValue(coin, v));
    }

    public int minimumCoinChangeToGiveValue(final int[] coin, final int v) {
        if (v == 0) {
            return 0;
        }
        final int[][] dp = new int[coin.length + 1][v + 1];
        final int max = Integer.MAX_VALUE - 1;

        // Initialize 1st row and column
        for (int j = 0; j < v + 1; j++) {
            dp[0][j] = max;
        }
        for (int i = 1; i < coin.length + 1; i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i < coin.length + 1; i++) {
            for (int j = 1; j < v + 1; j++) {
                if (coin[i - 1] <= j) {
                    dp[i][j] = Math.min(1 + dp[i][j - coin[i - 1]], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[coin.length][v];
    }
}
