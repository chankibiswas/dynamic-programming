package com.example.algorithm.dynamicProgramming.stock;

/*
You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.
Find the maximum profit you can achieve. You may complete at most k transactions.
Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
*
Input: k = 2, prices = [3,2,6,5,0,3]
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and
sell on day 6 (price = 3), profit = 3-0 = 3.
 */
public class StockBuySellProblem4 {

    private Integer[][][] dp;

    public static void main(final String[] s) {
        final int[] prices = {1, 2, 4, 2, 5, 7, 2, 4, 9, 0};
        final StockBuySellProblem4 sp = new StockBuySellProblem4();
        System.out.println("Max profit for 2 transactions: " + sp.maxProfit(prices, 4));
    }

    private int maxProfit(final int[] prices, final int k) {
        if (prices.length <= 1 || k < 1) {
            return 0;
        } else if (prices.length > 1 && prices.length <= 3) {
            int max = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i - 1] < prices[i]) {
                    max = max + (prices[i] - prices[i - 1]);
                }
            }
            return max;
        } else {
            dp = new Integer[prices.length + 1][2][k + 1];
            final int max = calculateProfit(prices, 0, 1, 0, k);
            return max;
        }
    }

    private int calculateProfit(final int[] prices, final int i, final int buy, final int txCount, final int k) {
        if (i >= prices.length || txCount >= k) {
            return 0;
        }
        if (dp[i][buy][txCount] != null) {
            return dp[i][buy][txCount];
        }
        if (buy == 1) {
            // Either you buy or don't at 'i'th index. If Buy done, then '-prices[i]' is added to calculate profit from txn.
            final int temp = Math.max(-prices[i] + calculateProfit(prices, i + 1, 0, txCount, k),
                                      calculateProfit(prices, i + 1, 1, txCount, k));
            dp[i][buy][txCount] = temp;
            return temp;
        } else {
            // Either you sell here or don't sell
            final int temp = Math.max(prices[i] + calculateProfit(prices, i + 1, 1, txCount + 1, k),
                                      calculateProfit(prices, i + 1, 0, txCount, k));
            dp[i][buy][txCount] = temp;
            return temp;
        }
    }
}
