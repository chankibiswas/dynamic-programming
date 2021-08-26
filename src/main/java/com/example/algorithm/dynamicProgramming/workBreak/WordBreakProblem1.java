package com.example.algorithm.dynamicProgramming.workBreak;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence
of one or more dictionary words.
Note that the same word in the dictionary may be reused multiple times in the segmentation.
*
Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
*
Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false
*
Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.
 */
public class WordBreakProblem1 {

    private final Map<String, Boolean> dp = new HashMap<>();

    public static void main(String[] st) {
        String s = "catsanddog";
        List<String> wordDict = Arrays.asList("cat", "cats", "and", "sand", "dog");
        System.out.println(new WordBreakProblem1().wordBreak2(s, wordDict));
    }

    /*
    Conservative way to break down all letters from start index and check if they are present in Dictionary
    Here a dp Map is used to store all subset words and their result
    This method is much time and space consuming
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s.length() == 0) {
            return true;
        }
        if (wordDict.contains(s)) {
            dp.put(s, true);
            return true;
        }
        if (dp.containsKey(s)) {
            return dp.get(s);
        }
        boolean result = false;
        for (int i = 1; i < s.length(); i++) {
            // Check if first part is valid word, then only move to later part
            boolean b1 = wordDict.contains(s.substring(0, i));
            if (b1) {
                dp.put(s.substring(0, i), b1);
                boolean b2 = wordDict.contains(s.substring(i)) || wordBreak(s.substring(i), wordDict);
                dp.put(s.substring(i), b2);
                dp.put(s, b1 && b2);
                result = b1 && b2;
                if (result) {
                    break;
                }
            }
        }
        return result;
    }

    /*
    This method is much better to previous one.
    No recursion involved here.
    Time & space complexity is much lesser than the above method
     */
    public boolean wordBreak2(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n];

        //dp [i] represents if such a word break is possible starting at character i.
        for (int i = n - 1; i >= 0; i--) {
            boolean val = false;
            for (String str : wordDict) {
                if (s.startsWith(str, i)) {
                    if (i + str.length() == dp.length) {
                        val = true;
                        break;
                    } else if (i + str.length() < dp.length && dp[i + str.length()]) {
                        val = dp[i + str.length()];
                        break;
                    }
                }
            }
            dp[i] = val;
        }
        return dp[0];
    }
}
