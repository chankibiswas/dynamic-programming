package com.example.algorithm.dynamicProgramming.zeroOneKnapsack;

public class EqualSumPartition {

    public static void main(final String[] s) {
        final EqualSumPartition e = new EqualSumPartition();
        final int[] items = {1, 11, 5, 2, 3};
        System.out.println(e.equalSumPartition(items));
    }

    public boolean equalSumPartition(final int[] items) {
        int sum = 0;
        for (int i = 0; i < items.length; i++) {
            sum = sum + items[i];
        }
        if (sum % 2 == 0) {
            return new SubsetSumProblem().subsetSumProblemWithTopDown(items, sum / 2);
        } else {
            return false;
        }
    }
}
