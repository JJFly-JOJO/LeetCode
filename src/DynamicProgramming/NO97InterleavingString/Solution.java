package DynamicProgramming.NO97InterleavingString;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/17 15:44
 * @description   --------------不要被交叉放入的过程所迷惑-----------
 * 思路：对于两个字符串将子串交错组合成另个一字符串，实际就是每次从两个字符串中按顺序选取一个字符出来，不论怎么选，最后组成的
 * 字符串只有两个字符串中的字母组成，必然是交错的（只有两个以上字符串的抽取，才会存在ABABC的不交错问题）
 */
public class Solution {

    public boolean isInterleave(String s1, String s2, String s3) {
        char[] c1 = s1.toCharArray();
        int l1 = c1.length;
        char[] c2 = s2.toCharArray();
        int l2 = c2.length;
        char[] c3 = s3.toCharArray();
        int l3 = c3.length;
        if (l1 + l2 != l3) {
            return false;
        }
        boolean[][] dp = new boolean[l1 + 1][l2 + 1];
        //initialize
        dp[0][0] = true;
        for (int l = 1; l <= l3; l++) {
            int subL = l - 1;
            for (int i = 0; i <= subL && i <= l1; i++) {
                int j = subL - i;
                if (j <= l2) {
                    if (dp[i][j]) {
                        if (i < l1 && c3[l - 1] == c1[i]) {
                            dp[i + 1][j] = true;
                        }
                        if (j < l2 && c3[l - 1] == c2[j]) {
                            dp[i][j + 1] = true;
                        }
                    }
                }
            }
        }
        for (int i = 0; i <= l3 && i <= l1; i++) {
            int j = l3 - i;
            if (j <= l2 && dp[i][j]) {
                return true;
            }
        }
        return false;
    }

}
