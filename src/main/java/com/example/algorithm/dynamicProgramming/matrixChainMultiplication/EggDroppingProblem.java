package com.example.algorithm.dynamicProgramming.matrixChainMultiplication;

import java.util.HashMap;
import java.util.Map;

/*
Egg Dropping problem
Problem statement: You are given F floor and E eggs.
You have to minimize the number of times you have to drop the eggs to
find the critical floor (critical floor means the floor beyond which eggs start to break).

Assumptions of the problem:
If egg breaks at ith floor then it also breaks at all greater floors.
If egg does not break at ith floor then it does not break at all lower floors.
Unbroken egg can be used again.
You have to consider worst case for calculating minimum attempts.
Note: You have to find minimum trials required to find the critical floor not the critical floor.

Example:
Input:
    4
    2
Output: Number of trials when number of eggs is 2 and number of floors is 4: 3
 */
public class EggDroppingProblem {

    private static final String COLON = ":";
    private final Map<String, Integer> dp = new HashMap<>();

    public static void main(final String[] s) {
        final EggDroppingProblem edp = new EggDroppingProblem();
        final int noOfEggs = 3;
        final int noOfFloors = 7;
        System.out.println(edp.getMinimumTrialsToGetCriticalFloor(noOfEggs, noOfFloors));
    }

    private int getMinimumTrialsToGetCriticalFloor(final int e, final int f) {
        if (f == 0 || f == 1) {
            return f;
        }
        // If there's 1 egg, we have to start from bottom most floor and the worst case would be trials equal to number of
        // floors
        if (e == 1) {
            return f;
        }
        final String key = e + COLON + f;
        if (dp.containsKey(key)) {
            return dp.get(key);
        }
        int min = Integer.MAX_VALUE;
        for (int k = 1; k <= f; k++) {
            final String lowKey = (e - 1) + COLON + (k - 1);
            final int low = (dp.containsKey(lowKey)) ? dp.get(lowKey) : getMinimumTrialsToGetCriticalFloor(e - 1, k - 1);

            final String highKey = e + COLON + (f - k);
            final int high = (dp.containsKey(highKey)) ? dp.get(highKey) : getMinimumTrialsToGetCriticalFloor(e, f - k);

            // It's Math.max because we need to find the worst case for the number of trials
            final int temp = 1 + Math.max(low, high);
            min = Math.min(min, temp);
        }
        dp.put(key, min);
        return min;
    }
}
