package String.NO05LongestPalindromicSubstring;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/22 0:06
 * @description --------------暴力搜索  优化办法 搜索的时候先不要subString 我们用index记录左右边界值--------
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().longestPalindrome("bananas"));
    }

    public String longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        int res = 1;
        int l = 0;
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        for (int i = 1; i < len; i++) {
            int head = i - dp[i - 1] - 1;
            if (head >= 0 && chars[head] == chars[i]) {
                dp[i] = dp[i - 1] + 2;
                if (res < dp[i]) {
                    res = dp[i];
                    l = head;
                }
                continue;
            }
            int start = i - dp[i - 1];
            if (isSame(start, i, chars)) {
                dp[i] = dp[i - 1] + 1;
                if (res < dp[i]) {
                    res = dp[i];
                    l = start;
                }
            }
        }
        return new String(chars, l, res);
    }

    private boolean isSame(int start, int end, char[] chars) {
        for (int i = start + 1; i <= end; i++) {
            if (chars[i] != chars[i - 1]) {
                return false;
            }
        }
        return true;
    }

}
