package String.NO132PalindromePartitioningII;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/21 10:41
 * @description -------------加1类型的动态规划-----------
 */
public class Solution {

    public int minCut(String s) {
        char[] chars = s.toCharArray();
        boolean[][] checkPalindrome = new boolean[chars.length][chars.length];
        //预处理 判断所有可能的回文串情况
        for (int r = 0; r < chars.length; r++) {
            for (int l = 0; l <= r; l++) {
                if (chars[r] == chars[l] && (r < l + 2 || checkPalindrome[l + 1][r - 1])) {
                    checkPalindrome[l][r] = true;
                }
            }
        }
        //动态规划 判断最小分割
        int[] dp = new int[chars.length];
        for (int r = 0; r < chars.length; r++) {
            if (checkPalindrome[0][r]) {
                dp[r] = 0;
                continue;
            }
            int min = Integer.MAX_VALUE;
            //枚举所有到右边界r的子串
            for (int l = 1; l <= r; l++) {
                if (checkPalindrome[l][r]) {
                    min = Math.min(min, dp[l - 1] + 1);
                }
            }
            dp[r] = min;
        }
        return dp[chars.length - 1];
    }

}
