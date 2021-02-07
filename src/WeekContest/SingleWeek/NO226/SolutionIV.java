package WeekContest.SingleWeek.NO226;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/31 12:12
 * @description
 */
public class SolutionIV {

    public boolean checkPartitioning(String s) {
        char[] chars = s.toCharArray();
        boolean[][] dp = new boolean[chars.length][chars.length];
        for (int i = 0; i < chars.length; i++) {
            dp[i][i] = true;
        }
        for (int i = 2; i <= chars.length; i++) {
            int last = chars.length - i;
            for (int j = 0; j <= last; j++) {
                int k = j + i - 1;
                if (chars[j] == chars[k] && (j + 1 >= k - 1 || dp[j + 1][k - 1])) {
                    dp[j][k] = true;
                }
            }
        }
        for (int i = 0; i < chars.length - 2; i++) {
            if (dp[0][i]) {
                for (int j = i + 1; j < chars.length - 1; j++) {
                    if (dp[i + 1][j] && dp[j + 1][chars.length - 1]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
