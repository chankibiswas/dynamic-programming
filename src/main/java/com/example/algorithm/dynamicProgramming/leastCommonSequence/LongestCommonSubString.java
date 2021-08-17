package com.example.algorithm.dynamicProgramming.leastCommonSequence;

/*
Longest Common Substring(Dynamic Programming)
Given two strings ‘X’ and ‘Y’, find the length of the longest common substring.
Examples:
Input : X = “GeeksforGeeks”, y = “GeeksQuiz”
Output : 5
The longest common substring is “Geeks” and is of length 5
 */
public class LongestCommonSubString {

    public static void main(final String[] s) {
        final String s1 = "abcdfeg";
        final String s2 = "abdefgh";
        System.out.println(new LongestCommonSubString().getLongestCommonSubStringLength(s1, s2));
    }

    private int getLongestCommonSubStringLength(final String s1, final String s2) {
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
                    dp[i][j] = 0;
                }
            }
        }
        int max = 0;
        for (int i = 1; i < s1.length() + 1; i++) {
            for (int j = 1; j < s2.length() + 1; j++) {
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

}
