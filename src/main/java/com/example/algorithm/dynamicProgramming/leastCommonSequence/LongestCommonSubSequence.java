package com.example.algorithm.dynamicProgramming.leastCommonSequence;

/*
Longest Common Sub-sequence Problem
Given two sequences, find the length of longest sub-sequence present in both of them.
A sub-sequence is a sequence that appears in the same relative order, but not necessarily contiguous.
For example, “abc”,  “abg”, “bdf”, “aeg”, "cefg”, .. etc are subsequences of “abcdefg”.
 */
public class LongestCommonSubSequence {

    public static void main(final String[] s) {
        final LongestCommonSubSequence lcs = new LongestCommonSubSequence();
        final String s1 = "abcdefghi";
        final String s2 = "abcfgijklmno";

        System.out.println(lcs.longestCommonSubSequenceLengthByTopDown(s1, s2));
    }

    public int longestCommonSubSequenceLengthByRecursion(final String s1, final int l1, final String s2, final int l2) {
        if (l1 == 0 || l2 == 0) {
            return 0;
        }
        if (s1.charAt(l1 - 1) == s2.charAt(l2 - 1)) {
            return 1 + longestCommonSubSequenceLengthByRecursion(s1, l1 - 1, s2, l2 - 1);
        } else {
            return Math.max(longestCommonSubSequenceLengthByRecursion(s1, l1 - 1, s2, l2),
                            longestCommonSubSequenceLengthByRecursion(s1, l1, s2, l2 - 1));
        }
    }

    /*
    Initially the 2-D matrix 'dp' is fully initialized with -1
     */
    public int longestCommonSubSequenceLengthByMemoization(final String s1, final int l1, final String s2, final int l2,
                                                           final int[][] dp) {
        if (l1 == 0 || l2 == 0) {
            return 0;
        }
        if (dp[l1][l2] != -1) {
            return dp[l1][l2];
        }
        if (s1.charAt(l1 - 1) == s2.charAt(l2 - 1)) {
            dp[l1][l2] = 1 + longestCommonSubSequenceLengthByMemoization(s1, l1 - 1, s2, l2 - 1, dp);
        } else {
            dp[l1][l2] = Math.max(longestCommonSubSequenceLengthByMemoization(s1, l1 - 1, s2, l2, dp),
                                  longestCommonSubSequenceLengthByMemoization(s1, l1, s2, l2 - 1, dp));
        }
        return dp[l1][l2];
    }

    public int longestCommonSubSequenceLengthByTopDown(final String s1, final String s2) {
        if (s1.length() == 0 || s2.length() == 0) {
            return 0;
        }

        final int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i < s1.length() + 1; i++) {
            dp[i][0] = 0;
        }
        for (int j = 1; j < s2.length() + 1; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i < s1.length() + 1; i++) {
            for (int j = 1; j < s2.length() + 1; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }
}
