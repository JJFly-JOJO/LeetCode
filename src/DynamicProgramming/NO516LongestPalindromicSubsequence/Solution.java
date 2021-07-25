package DynamicProgramming.NO516LongestPalindromicSubsequence;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/7/4 0:46
 * @description
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().longestPalindromeSubseq("cbbd"));
    }

    public int longestPalindromeSubseq(String s) {
        char[] sChar = s.toCharArray();
        int slen = sChar.length;
        int[][] dp = new int[slen][slen];
        //initialize
        for (int i = 0; i < slen; i++) {
            dp[i][i] = 1;
            if (i != 0) {
                if (sChar[i] == sChar[i - 1]) {
                    dp[i][i - 1] = 2;
                } else {
                    dp[i][i - 1] = 1;
                }
            }
        }
        for (int i = 2; i < slen; i++) {
            for (int j = i - 2; j >= 0; j--) {
                if (sChar[i] == sChar[j]) {
                    dp[i][j] = Math.max(dp[i - 1][j + 1] + 2, dp[i - 1][j]);
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j + 1]);
                }
            }
        }
        return dp[slen - 1][0];
    }

}
