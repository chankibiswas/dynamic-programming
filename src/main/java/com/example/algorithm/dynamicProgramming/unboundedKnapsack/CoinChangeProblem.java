package com.example.algorithm.dynamicProgramming.unboundedKnapsack;

/*
Coin Change Problem, Maximum Number of ways to achieve a given sum by coins. We have infinite supply of each coin.
How many ways can we make the change?
Also the order of coins doesn’t matter.
Example: for sum = 4 and coin[] = {1,2,3}, i.e. there are coins of value 1, 2 and 3.
there are four solutions: {1,1,1,1},{1,1,2},{2,2},{1,3}. So output should be 4.
 */
public class CoinChangeProblem {

    public static void main(final String[] s) {
        final int[] coin = {1, 2, 3};
        final int sum = 4;
        System.out.println(new CoinChangeProblem().getMaximumNumberOfWaysToGetSumThroughCoinChange(coin, sum));
    }

    private int getMaximumNumberOfWaysToGetSumThroughCoinChange(final int[] coin, final int sum) {
        if (sum == 0) {
            return 0;
        }
        if (coin.length == 0) {
            return 0;
        }

        final int[][] dp = new int[coin.length + 1][sum + 1];

        for (int i = 0; i < coin.length + 1; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; j < sum + 1; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i < coin.length + 1; i++) {
            for (int j = 1; j < sum + 1; j++) {
                if (coin[i - 1] <= j) {
                    dp[i][j] = dp[i][j - coin[i - 1]] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[coin.length][sum];
    }
}
