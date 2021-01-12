package WeekContest.DoubleWeek.NO38;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/5 10:04
 * @description -----------动态规划 这里用两个二维dp来记录两种子问题结果---------
 */
public class Solution3V2 {

    public int countSubstrings(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();
        int[][] subDp = new int[sLen + 1][tLen + 1];
        int[][] preDp = new int[sLen + 1][tLen + 1];
        int res = 0;
        //initialize
        for (int i = 1; i <= sLen; i++) {
            for (int j = 1; j <= tLen; j++) {
                if (sChar[i - 1] == tChar[j - 1]) {
                    subDp[i][j] = subDp[i - 1][j - 1];
                    preDp[i][j] = preDp[i - 1][j - 1] + 1;
                } else {
                    subDp[i][j] = preDp[i - 1][j - 1] + 1;
                    preDp[i][j] = 0;
                }
                res += subDp[i][j];
            }
        }
        return res;
    }

    /**
     * 根据写出来的动态规划代码 我们可以很容易看出 可以进行状态压缩
     *
     * @param s
     * @param t
     * @return
     */
    public int countSubstringsP(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();
        //int[][] subDp = new int[sLen + 1][tLen + 1];
        //int[][] preDp = new int[sLen + 1][tLen + 1];
        int res = 0;
        //initialize
        for (int i = 1; i <= sLen; i++) {
            int subDp = 0;
            int preDp = 0;
            for (int j = 1; j <= tLen; j++) {
                if (sChar[i - 1] == tChar[j - 1]) {
                    preDp++;
                } else {
                    subDp = preDp + 1;
                    preDp = 0;
                }
                res += subDp;
            }
        }
        return res;
    }

}
