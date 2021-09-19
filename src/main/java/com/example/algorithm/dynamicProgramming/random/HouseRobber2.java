package com.example.algorithm.dynamicProgramming.random;

import java.util.Arrays;

/*
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All
houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile,
adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses
were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob
tonight without alerting the police.
*
Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
*
Input: nums = [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
*
Input: nums = [1,2,3]
Output: 3
 */
public class HouseRobber2 {

    public static void main(String[] s) {
        int[] nums = {1, 2, 3, 1};
        System.out.println(rob(nums));
    }

    /*
    Inorder to break the circular condition, we call same function as House Robber problem 1 two times,
    with index 0 to second last and then first to last.
    Then just take the max of both of them.
     */
    public static int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        int first = rob(nums, 0, dp, 0, nums.length - 2);
        Arrays.fill(dp, -1);
        int second = rob(nums, 1, dp, 1, nums.length - 1);
        return Math.max(first, second);
    }

    private static int rob(final int[] nums, final int i, int[] dp, int start, int end) {
        if (i > end || i < start) {
            return 0;
        }
        if (dp[i] != -1) {
            return dp[i];
        }
        int p = nums[i] + rob(nums, i + 2, dp, start, end);
        int q = rob(nums, i + 1, dp, start, end);
        dp[i] = Math.max(p, q);
        return dp[i];
    }
}
