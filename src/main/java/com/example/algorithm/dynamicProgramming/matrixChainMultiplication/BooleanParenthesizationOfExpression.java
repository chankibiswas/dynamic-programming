package com.example.algorithm.dynamicProgramming.matrixChainMultiplication;

import java.util.HashMap;
import java.util.Map;

/*
Evaluate Expression To True-Boolean Parenthesization
Given a boolean expression with following symbols.
Symbols
    'T' --- true
    'F' --- false
And following operators filled between symbols
Operators
    &   --- boolean AND
    |   --- boolean OR
    ^   --- boolean XOR
Count the number of ways we can parenthesize the expression so that the value of expression evaluates to true.
Example:
Input: symbol[]    = {T, F, T}
       operator[]  = {^, &}
Output: 2
The given expression is "T ^ F & T", it evaluates true
in two ways "((T ^ F) & T)" and "(T ^ (F & T))"
 */
public class BooleanParenthesizationOfExpression {

    private final Map<String, Integer> dp = new HashMap<>();
    private final String COLON = ":";

    public static void main(final String[] st) {
        final BooleanParenthesizationOfExpression b = new BooleanParenthesizationOfExpression();
        final String s = "T^F|F&T";
        final char[] arr = s.toCharArray();
        System.out.println(b.countNumberOfWaysToParenthesize(arr, 0, arr.length - 1, true));
    }

    /*
    This is Bottom-Up method where a Map is used to store memoized values
     */
    public int countNumberOfWaysToParenthesize(final char[] arr, final int i, final int j, final boolean isTrue) {
        final String dpKey = i + COLON + j + COLON + isTrue;
        if (dp.containsKey(dpKey)) {
            return dp.get(dpKey);
        }
        if (i > j) {
            dp.put(dpKey, 0);
            return 0;
        }
        if (i == j) {
            if (isTrue) {
                final int temp1 = (arr[i] == 'T') ? 1 : 0;
                dp.put(dpKey, temp1);
                return temp1;
            } else {
                final int temp2 = (arr[i] == 'T') ? 0 : 1;
                dp.put(dpKey, temp2);
                return temp2;
            }
        }
        int ans = 0;
        for (int k = i + 1; k < j; k = k + 2) {
            final String partialLeftTrueKey = i + COLON + (k - 1) + COLON + true;
            final String partialLeftFalseKey = i + COLON + (k - 1) + COLON + false;
            final String partialRightTrueKey = (k + 1) + COLON + j + COLON + true;
            final String partialRightFalseKey = (k + 1) + COLON + j + COLON + false;

            final int leftTrue = dp.containsKey(partialLeftTrueKey) ?
                                 dp.get(partialLeftTrueKey) :
                                 countNumberOfWaysToParenthesize(arr, i, k - 1, true);
            final int leftFalse = dp.containsKey(partialLeftFalseKey) ?
                                  dp.get(partialLeftFalseKey) :
                                  countNumberOfWaysToParenthesize(arr, i, k - 1, false);
            final int rightTrue = dp.containsKey(partialRightTrueKey) ?
                                  dp.get(partialRightTrueKey) :
                                  countNumberOfWaysToParenthesize(arr, k + 1, j, true);
            final int rightFalse = dp.containsKey(partialRightFalseKey) ?
                                   dp.get(partialRightFalseKey) :
                                   countNumberOfWaysToParenthesize(arr, k + 1, j, false);

            final char operator = arr[k];

            if (operator == '&') {
                if (isTrue) {
                    ans = ans + leftTrue * rightTrue;
                } else {
                    ans = ans + (leftFalse * rightFalse) + (leftFalse * rightTrue) + (leftTrue * rightFalse);
                }
            } else if (operator == '|') {
                if (isTrue) {
                    ans = ans + (leftTrue * rightTrue) + (leftFalse * rightTrue) + (leftTrue * rightFalse);
                } else {
                    ans = ans + (leftFalse * rightFalse);
                }
            } else if (operator == '^') {
                if (isTrue) {
                    ans = ans + (leftFalse * rightTrue) + (leftTrue * rightFalse);
                } else {
                    ans = ans + (leftFalse * rightFalse) + (leftTrue * rightTrue);
                }
            }
        }
        dp.put(dpKey, ans);
        return ans;
    }
}
