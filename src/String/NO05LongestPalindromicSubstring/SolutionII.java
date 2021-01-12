package String.NO05LongestPalindromicSubstring;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/23 11:42
 * @description
 */
public class SolutionII {

    public String longestPalindrome(String s) {
        int len = s.length();
        char[] chars = s.toCharArray();
        boolean[][] dp = new boolean[len][len];
        //initialize
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        int begin = 0;
        int l = 1;
        //dp
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                int a = j + 1;
                int b = i - 1;
                if (chars[j] == chars[i]) {
                    if (a <= b && dp[a][b]) {
                        dp[j][i] = true;
                    }
                    if (a > b) {
                        dp[j][i] = true;
                    }
                }
                if (dp[j][i] && i - j + 1 > l) {
                    begin = j;
                    l = i - j + 1;
                }
            }
        }
        return new String(chars, begin, l);
    }

}
