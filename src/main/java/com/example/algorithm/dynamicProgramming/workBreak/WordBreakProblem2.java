package com.example.algorithm.dynamicProgramming.workBreak;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word is a valid
dictionary word. Return all such possible sentences in any order.
*
Note that the same word in the dictionary may be reused multiple times in the segmentation.
*
Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
Output: ["cats and dog","cat sand dog"]
*
Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
Explanation: Note that you are allowed to reuse a dictionary word.
*
Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: []
 */
public class WordBreakProblem2 {

    private static final String SPACE = " ";

    private final Map<String, List<String>> dp = new HashMap<>();

    public static void main(String[] str) {
        String s = "catsanddog";
        List<String> wordDict = Arrays.asList("cat", "cats", "and", "sand", "dog");
        List<String> res = new WordBreakProblem2().wordBreak(s, wordDict);
        res.forEach(System.out::println);
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        if (dp.containsKey(s)) {
            return dp.get(s);
        }
        List<String> res = new ArrayList<>();
        if (wordDict.contains(s)) {
            res.add(s);
        }
        for (int i = 1; i < s.length(); i++) {
            String left = s.substring(0, i);
            if (wordDict.contains(left)) {
                List<String> subResults = wordBreak(s.substring(i), wordDict);
                for (String sr : subResults) {
                    res.add(left + SPACE + sr);
                }
            }
        }
        dp.put(s, res);
        return res;
    }
}
