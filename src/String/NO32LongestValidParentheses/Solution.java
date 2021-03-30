package String.NO32LongestValidParentheses;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/28 1:08
 * @description -----------动态规划解法---------
 */
public class Solution {

    public int longestValidParentheses(String s) {
        char[] chars = s.toCharArray();
        int[] dp = new int[chars.length + 1];
        int res = 0;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == '(') {
                dp[i + 1] = 0;
            } else {
                //')'
                if (chars[i - 1] == '(') {
                    dp[i + 1] = dp[i - 1] + 2;
                } else {
                    //')'
                    int index = i - 1 - dp[i];
                    if (index >= 0 && chars[index] == '(') {
                        //example:")()(()))"
                        dp[i + 1] = dp[i] + 2 + dp[index];
                    } else {
                        dp[i + 1] = 0;
                    }
                }
            }
            res = Math.max(res, dp[i + 1]);
        }
        return res;
    }

}
