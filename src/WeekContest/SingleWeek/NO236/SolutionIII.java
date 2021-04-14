package WeekContest.SingleWeek.NO236;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/4/11 11:14
 * @description
 */
public class SolutionIII {

    public int minSideJumps(int[] obstacles) {
        //dp 到达当前位置的最小侧跳次数
        int[][] dp = new int[obstacles.length][3];
        for (int[] i : dp) {
            //这里不使用Integer.MAX_VALUE 防止示例1中 走到下标2位置处 如果先计算的是道路三的最小侧跳次数 那么会发生整数溢出 变为负数
            Arrays.fill(i, 10000000);
        }
        //initialize
        dp[0][0] = 1;
        dp[0][1] = 0;
        dp[0][2] = 1;
        for (int i = 1; i < obstacles.length; i++) {
            if (obstacles[i] != 1) {
                dp[i][0] = dp[i - 1][0];
            }
            if (obstacles[i] != 2) {
                dp[i][1] = dp[i - 1][1];
            }
            if (obstacles[i] != 3) {
                dp[i][2] = dp[i - 1][2];
            }
            if (obstacles[i] != 1) {
                dp[i][0] = Math.min(dp[i][0], Math.min(dp[i][1], dp[i][2]) + 1);
            }
            if (obstacles[i] != 2) {
                dp[i][1] = Math.min(dp[i][1], Math.min(dp[i][0], dp[i][2]) + 1);
            }
            if (obstacles[i] != 3) {
                dp[i][2] = Math.min(dp[i][2], Math.min(dp[i][0], dp[i][1]) + 1);
            }
        }
        return Math.min(dp[obstacles.length - 1][0], Math.min(dp[obstacles.length - 1][1], dp[obstacles.length - 1][2]));
    }

}
