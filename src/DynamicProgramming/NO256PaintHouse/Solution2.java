package DynamicProgramming.NO256PaintHouse;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/30 16:48
 * @description ------------状态压缩-------------
 */
public class Solution2 {

    public int minCost(int[][] costs) {
        int num = costs.length;
        if (num == 0) {
            return 0;
        }
        int[] dp = new int[3];
        //initialize
        System.arraycopy(costs[0], 0, dp, 0, 3);
        //dp
        for (int i = 1; i < num; i++) {
            int c0 = Math.min(dp[1], dp[2]) + costs[i][0];
            int c1 = Math.min(dp[0], dp[2]) + costs[i][1];
            int c2 = Math.min(dp[0], dp[1]) + costs[i][2];
            dp[0] = c0;
            dp[1] = c1;
            dp[2] = c2;
        }
        return Math.min(Math.min(dp[0], dp[1]), dp[2]);
    }

}
