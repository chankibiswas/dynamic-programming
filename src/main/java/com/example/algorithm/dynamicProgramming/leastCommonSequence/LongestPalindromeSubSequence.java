package com.example.algorithm.dynamicProgramming.leastCommonSequence;

/*
Longest Palindromic Sub-sequence
Given a sequence, find the length of the longest palindromic sub-sequence in it.
Example :
Input:"bbbab"
Output:4
Here "bbbb" is a palindrome sub-sequence
 */
public class LongestPalindromeSubSequence {

    public static void main(final String[] s) {
        final String s1 = "agbcba";
        final LongestPalindromeSubSequence longestPalindromeSubSequence = new LongestPalindromeSubSequence();
        System.out.println(longestPalindromeSubSequence.lengthOfLongestPalindromeSubSequence(s1));
        longestPalindromeSubSequence.getMinimumDeletionForLongestPalindromeSubSequence(s1);
    }

    public int lengthOfLongestPalindromeSubSequence(final String s1) {
        // First take LCS of String and reverse of that String. The LCS length will give length of longest palindrome
        // sub-sequence
        final LongestCommonSubSequence longestCommonSubSequence = new LongestCommonSubSequence();
        final int lcs =
            longestCommonSubSequence.longestCommonSubSequenceLengthByTopDown(s1, new StringBuilder(s1).reverse().toString());
        return lcs;
    }

    public void getMinimumDeletionForLongestPalindromeSubSequence(final String s1) {
        final int longestPalindromeLength = lengthOfLongestPalindromeSubSequence(s1);
        System.out.println("Deletion = " + (s1.length() - longestPalindromeLength));
    }
}
