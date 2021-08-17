package com.example.algorithm.dynamicProgramming.leastCommonSequence;

/*
Minimum number of deletions and insertions to transform one string into another
Given two strings ‘str1’ and ‘str2’ of size m and n respectively. The task is to remove/delete and insert minimum number
of characters from/in str1 so as to transform it into str2. It could be possible that the same character needs to be
removed/deleted from one point of str1 and inserted to some another point.
Example:
Input : str1 = "geeksforgeeks", str2 = "geeks"
Output : Minimum Deletion = 8
         Minimum Insertion = 0

Input : str1 = "heap", str2 = "pea"
Output : Minimum Deletion = 2
         Minimum Insertion = 1
 */
public class ConvertStringAToB {

    public static void main(final String[] s) {
        final String s1 = "heap";
        final String s2 = "pea";
        new ConvertStringAToB().printNumberOfInsertionDeletionToConvertAToB(s1, s2);
    }

    public void printNumberOfInsertionDeletionToConvertAToB(final String s1, final String s2) {
        final LongestCommonSubSequence longestCommonSubSequence = new LongestCommonSubSequence();
        final int lcs = longestCommonSubSequence.longestCommonSubSequenceLengthByTopDown(s1, s2);
        System.out.println("Deletion = " + (s1.length() - lcs));
        System.out.println("Insertion = " + (s2.length() - lcs));
    }

}
