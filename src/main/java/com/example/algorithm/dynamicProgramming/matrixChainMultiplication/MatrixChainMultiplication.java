package com.example.algorithm.dynamicProgramming.matrixChainMultiplication;

/*
Matrix Chain Multiplication
Given a sequence of matrices, find the most efficient way to multiply these matrices together.
The problem is not actually to perform the multiplications, but merely to decide in which order to perform the
multiplications.
 */
public class MatrixChainMultiplication {

    public static void main(final String[] s) {
        final MatrixChainMultiplication mcm = new MatrixChainMultiplication();
        final int[] m = {40, 20, 30, 10, 30};
        System.out.println(mcm.getMatrixChainMultiplicationSolutionRecursive(m, 1, m.length - 1));

        final int[][] dp = new int[m.length][m.length];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                dp[i][j] = -1;
            }
        }
        System.out.println(mcm.getMatrixChainMultiplicationSolutionBottomUp(m, 1, m.length - 1, dp));
    }

    public int getMatrixChainMultiplicationSolutionRecursive(final int[] m, final int i, final int j) {
        if (i >= j) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            final int temp = getMatrixChainMultiplicationSolutionRecursive(m, i, k) +
                             getMatrixChainMultiplicationSolutionRecursive(m, k + 1, j) +
                             m[i - 1] * m[k] * m[j];
            if (temp < min) {
                min = temp;
            }
        }
        return min;
    }

    public int getMatrixChainMultiplicationSolutionBottomUp(final int[] m, final int i, final int j, final int[][] dp) {
        if (i >= j) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int min = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            final int temp = getMatrixChainMultiplicationSolutionBottomUp(m, i, k, dp) +
                             getMatrixChainMultiplicationSolutionBottomUp(m, k + 1, j, dp) +
                             m[i - 1] * m[k] * m[j];
            if (temp < min) {
                min = temp;
            }
        }
        dp[i][j] = min;
        return dp[i][j];
    }
}
