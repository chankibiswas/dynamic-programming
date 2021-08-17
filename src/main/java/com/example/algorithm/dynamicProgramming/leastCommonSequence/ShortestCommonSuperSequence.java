package com.example.algorithm.dynamicProgramming.leastCommonSequence;

/*
Shortest Common Super-sequence
Given two strings str1 and str2, find the shortest string that has both str1 and str2 as sub-sequences.
Examples:
Input:   str1 = "geek",  str2 = "eke"
Output: "geeke" and it's length 5
 */
public class ShortestCommonSuperSequence {

    public static void main(final String[] s) {
        final String s1 = "geek";
        final String s2 = "eke";
        System.out.println(new ShortestCommonSuperSequence().lengthOfShortestCommonSuperSequence(s1, s2));
    }

    public int lengthOfShortestCommonSuperSequence(final String s1, final String s2) {
        final LongestCommonSubSequence lcs = new LongestCommonSubSequence();
        final int lengthOfLcs = lcs.longestCommonSubSequenceLengthByTopDown(s1, s2);

        return s1.length() + s2.length() - lengthOfLcs;
    }

}
