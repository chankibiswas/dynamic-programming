package com.example.algorithm.dynamicProgramming.matrixChainMultiplication;

import java.util.HashMap;
import java.util.Map;

/*
Scramble String
Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.
Below is one possible representation of A = “great”:
 great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t
To scramble the string, we may choose any non-leaf node and swap its two children.

For example, if we choose the node “gr” and swap its two children, it produces a scrambled string “rgeat”.

    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t
We say that “rgeat” is a scrambled string of “great”.

Similarly, if we continue to swap the children of nodes “eat” and “at”, it produces a scrambled string “rgtae”.

    rgtae
   /    \
  rg    tae
 / \    /  \
r   g  ta  e
       / \
      t   a
We say that “rgtae” is a scrambled string of “great”.
 */
public class ScrambleString {

    private static final String COLON = ":";
    private final Map<String, Boolean> dp = new HashMap<>();

    public static void main(final String[] s) {
        final ScrambleString ss = new ScrambleString();
        // Below 2 are anagrams but not scrambled
        final String s1 = "torfpuw";
        final String s2 = "prwouft";
        //System.out.println(ss.isScrambleString(s1, s2));

        // Below 2 are scrambled
        final String s3 = "great";
        final String s4 = "ategr";
        System.out.println(ss.isScrambleString(s3, s4));
    }

    public boolean isScrambleString(final String s1, final String s2) {
        final String key = s1 + COLON + s2;
        if (dp.containsKey(key)) {
            return dp.get(key);
        }
        if (s1.length() != s2.length()) {
            dp.put(key, false);
            return false;
        }
        if (s1.equals(s2)) {
            dp.put(key, true);
            return true;
        }
        if (s1.length() < 1 || s2.length() < 1) {
            dp.put(key, false);
            return false;
        }

        boolean scrambleFlag = false;
        final int length = s1.length();
        for (int i = 1; i < length; i++) {
            if ((isScrambleString(s1.substring(0, i), s2.substring(length - i)) &&
                 isScrambleString(s1.substring(i, length), s2.substring(0, length - i)))
                || ((isScrambleString(s1.substring(0, i), s2.substring(0, i))) &&
                    isScrambleString(s1.substring(i, length), s2.substring(i, length)))) {
                scrambleFlag = true;
                break;
            }
        }
        dp.put(key, scrambleFlag);
        return scrambleFlag;
    }

}
