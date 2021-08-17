package com.example.algorithm.dynamicProgramming.leastCommonSequence;

public class PrintShortestCommonSuperSequence {

    public static void main(final String[] s) {
        final String s1 = "geek";
        final String s2 = "eke";
        new PrintShortestCommonSuperSequence().getShortestCommonSuperSequence(s1, s2);
    }

    public void getShortestCommonSuperSequence(final String s1, final String s2) {
        final int[][] dp = createDpMatrix(s1, s2);
        int i = s1.length();
        int j = s2.length();
        final StringBuilder sb = new StringBuilder();

        // Retracing dp matrix to construct longest sub-sequence backwards and then reversing it for output
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                sb.append(s1.charAt(i - 1));
                i--;
                j--;
            } else {
                if (dp[i][j - 1] > dp[i - 1][j]) {
                    sb.append(s2.charAt(j - 1));
                    j--;
                } else {
                    sb.append(s1.charAt(i - 1));
                    i--;
                }
            }
        }
        while (i > 0) {
            sb.append(s1.charAt(i - 1));
            i--;
        }
        while (j > 0) {
            sb.append(s2.charAt(j - 1));
            j--;
        }
        System.out.println(sb.reverse().toString());
    }

    private int[][] createDpMatrix(final String s1, final String s2) {
        // Initialize data for empty Strings
        final int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i < s1.length() + 1; i++) {
            dp[i][0] = 0;
        }
        for (int j = 1; j < s2.length() + 1; j++) {
            dp[0][j] = 0;
        }

        // Complete dp matrix
        for (int i = 1; i < s1.length() + 1; i++) {
            for (int j = 1; j < s2.length() + 1; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp;
    }
}
