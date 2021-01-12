package DynamicProgramming.NO256PaintHouse;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/30 15:59
 * @description
 */
public class Solution {

    public int minCost(int[][] costs) {
        int num = costs.length;
        if (num == 0) {
            return 0;
        }
        int[][] dp = new int[num][3];
        //initialize
        System.arraycopy(costs[0], 0, dp[0], 0, 3);
        //dp
        for (int i = 1; i < num; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    dp[i][j] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][j];
                } else if (j == 1) {
                    dp[i][j] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][j];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i][j];
                }
            }
        }
        return Math.min(Math.min(dp[num - 1][0], dp[num - 1][1]), dp[num - 1][2]);
    }
}
