package WeekContest.DoubleWeek.NO37;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/18 17:19
 * @description ---------------------动态规划-----------------
 */
public class Solution3V2 {

    public static void main(String[] args) {
        System.out.println(new Solution3V2().numberOfSets(48, 12));
    }

    public int numberOfSets(int n, int k) {
        long[][] dp = new long[n + 1][k + 1];
        //initialize
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                long sum = 0;
                for (int m = i - 1; m >= 0; m--) {
                    sum += dp[m][j - 1];
                }
                dp[i][j] = dp[i - 1][j] + sum;
            }
        }
        return (int) (dp[n - 1][k] % (1e9 + 7L));
    }

}
