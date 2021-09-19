package com.example.algorithm.dynamicProgramming.random;

import java.util.Arrays;

/*
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the
only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it
will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob
tonight without alerting the police.
*
Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.
*
Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
 */
public class HouseRobber {

    public static void main(String[] s) {
        int[] nums =
            {114, 117, 207, 117, 235, 82, 90, 67, 143, 146, 53, 108, 200, 91, 80, 223, 58, 170, 110, 236, 81, 90, 222, 160,
             165, 195, 187, 199, 114, 235, 197, 187, 69, 129, 64, 214, 228, 78, 188, 67, 205, 94, 205, 169, 241, 202, 144,
             240};
        //int[] nums = {2, 7, 9, 3, 1};
        System.out.println(rob(nums));
    }

    public static int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        return rob(nums, 0, dp);
    }

    private static int rob(final int[] nums, final int i, int[] dp) {
        if (i >= nums.length || i < 0) {
            return 0;
        }
        if (dp[i] != -1) {
            return dp[i];
        }
        int p = nums[i] + rob(nums, i + 2, dp);
        int q = rob(nums, i + 1, dp);
        dp[i] = Math.max(p, q);
        return dp[i];
    }
}
