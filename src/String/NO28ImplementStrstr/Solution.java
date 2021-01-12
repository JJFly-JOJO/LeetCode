package String.NO28ImplementStrstr;

public class Solution {

    private int[][] dp;//动态dp数组

    public void KMP(String pat) {
        int M = pat.length();
        this.dp = new int[M][256];
        dp[0][pat.charAt(0)] = 1;//数组初始化都为0 对状态0先进行初始化
        //影子状态初始为0
        int X = 0;
        for (int j = 1; j < M; j++) {
            for (int c = 0; c < 256; c++) {//256<------------------------改进
                if (pat.charAt(j) == c)
                    dp[j][c] = j + 1;
                else
                    dp[j][c] = dp[X][c];
            }
            X = dp[X][pat.charAt(j)];
        }
    }


    public int strStr(String haystack, String needle) {
        int hayStackLength = haystack.length();
        int needlength = needle.length();
        if (needlength == 0)
            return 0;
//        if (hayStackLength != 0 && needlength == 0 || hayStackLength == 0 && needlength != 0)
//            return -1;
        //初始化
        KMP(needle);
        int index = 0;
        for (int i = 0; i < hayStackLength; i++) {
            index = dp[index][haystack.charAt(i)];
            if (index == needlength)
                return i - needlength + 1;
        }
        return -1;
    }
}
