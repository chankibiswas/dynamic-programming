package com.example.algorithm.dynamicProgramming.matrixChainMultiplication;

/*
Palindrome Partitioning
Given a string, a partitioning of the string is a palindrome partitioning if every substring of the partition is a
palindrome.
Example: “aba|b|bbabb|a|b|aba” is a palindrome partitioning of “ababbbabbababa”.
*/
public class PalindromePartitioning {

    public static void main(final String[] str) {
        final PalindromePartitioning p = new PalindromePartitioning();
        final String s = "ababbbabbababa";
        //System.out.println(p.getMinimumPalindromePartitionByRecursion(s, 0, s.length() - 1));

        final int[][] dp = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < s.length(); j++) {
                dp[i][j] = -1;
            }
        }
        System.out.println(p.getMinimumPalindromePartitionByOptimizedBottomUp(s, 0, s.length() - 1, dp));
        //System.out.println(p.getMinimumPalindromePartitionByBottomUp(s, 0, s.length() - 1, dp));
    }

    public int getMinimumPalindromePartitionByRecursion(final String s, final int i, final int j) {
        if (i >= j) {
            return 0;
        }
        if (isPalindrome(s, i, j)) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int k = i; k <= j - 1; k++) {
            final int temp =
                1 + getMinimumPalindromePartitionByRecursion(s, i, k) + getMinimumPalindromePartitionByRecursion(s, k + 1,
                                                                                                                 j);
            if (temp < min) {
                min = temp;
            }
        }
        return min;
    }

    public int getMinimumPalindromePartitionByBottomUp(final String s, final int i, final int j, final int[][] dp) {
        if (i >= j) {
            return 0;
        }
        if (isPalindrome(s, i, j)) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int min = Integer.MAX_VALUE;
        for (int k = i; k <= j - 1; k++) {
            final int temp = 1 + getMinimumPalindromePartitionByBottomUp(s, i, k, dp)
                             + getMinimumPalindromePartitionByBottomUp(s, k + 1, j, dp);
            if (temp < min) {
                min = temp;
            }
        }
        dp[i][j] = min;
        return dp[i][j];
    }

    public int getMinimumPalindromePartitionByOptimizedBottomUp(final String s, final int i, final int j, final int[][] dp) {
        if (i >= j) {
            return 0;
        }
        if (isPalindrome(s, i, j)) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int min = Integer.MAX_VALUE;
        for (int k = i; k <= j - 1; k++) {
            final int left, right;
            if (dp[i][k] != -1) {
                left = dp[i][k];
            } else {
                left = getMinimumPalindromePartitionByOptimizedBottomUp(s, i, k, dp);
            }
            if (dp[k + 1][j] != -1) {
                right = dp[k + 1][j];
            } else {
                right = getMinimumPalindromePartitionByOptimizedBottomUp(s, k + 1, j, dp);
            }
            final int temp = 1 + left + right;
            if (temp < min) {
                min = temp;
            }
        }
        dp[i][j] = min;
        return dp[i][j];
    }

    private boolean isPalindrome(final String s, int i, int j) {
        while (i <= j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
