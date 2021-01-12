package DynamicProgramming.NO72EditDistance;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/13 22:15
 * @description
 */
public class Solution {

    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        //initialize
        for (int i = 1; i <= len2; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= len1; i++) {
            dp[i][0] = i;
        }
        //dp
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                int a = dp[i][j - 1] + 1;
                int b = dp[i - 1][j] + 1;
                int add = word1.charAt(i - 1) == word2.charAt(j - 1) ? 0 : 1;
                int c = dp[i - 1][j - 1] + add;
                dp[i][j] = Math.min(Math.min(a, b), c);
            }
        }
        return dp[len1][len2];
    }

}
