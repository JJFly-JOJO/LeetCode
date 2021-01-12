package WeekContest.SingleWeek.NO207;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/22 20:36
 * ---------动态规划-----状态压缩-------
 */
public class Solution3 {

    public static void main(String[] args) {
        int[][] grid = new int[][]{{-1, -2, -3},
                {-2, -3, -3},
                {-3, -3, -2}};
        System.out.println(new Solution3().maxProductPath(grid));
    }

    public int maxProductPath(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        //用正负维度来消除后效性
        long[][] dp = new long[col + 1][2];
        boolean[] isNegative = new boolean[col + 1];
        boolean[] isPositive = new boolean[col + 1];
        //initialize
        dp[0][0] = 1;
        dp[0][1] = -1;
        isPositive[0] = true;
        for (int r = 1; r <= row; r++) {
            for (int c = 1; c <= col; c++) {
                //i=0 求正数时 i=1 求负数时
                int curNum = grid[r - 1][c - 1];
                if (curNum >= 0) {
                    if (isPositive[c - 1] && isPositive[c]) {
                        dp[c][0] = Math.max(dp[c - 1][0] * curNum, dp[c][0] * curNum);
                        isPositive[c] = true;
                    } else if (isPositive[c - 1]) {
                        dp[c][0] = dp[c - 1][0] * curNum;
                        isPositive[c] = true;
                    } else if (isPositive[c]) {
                        dp[c][0] = dp[c][0] * curNum;
                        isPositive[c] = true;
                    } else {
                        isPositive[c] = false;
                    }
                    if (isNegative[c - 1] && isNegative[c]) {
                        //负数必须存在
                        dp[c][1] = Math.min(dp[c - 1][1] * curNum, dp[c][1] * curNum);
                        isNegative[c] = true;
                    } else if (isNegative[c - 1]) {
                        dp[c][1] = dp[c - 1][1] * curNum;
                        isNegative[c] = true;
                    } else if (isNegative[c]) {
                        dp[c][1] = dp[c][1] * curNum;
                        isNegative[c] = true;
                    } else {
                        isNegative[c] = false;
                    }
                } else {
                    //当前数是负数
                    if (isNegative[c - 1] && isNegative[c]) {
                        dp[c][0] = Math.max(dp[c - 1][1] * curNum, dp[c][1] * curNum);
                        isPositive[c] = true;
                    } else if (isNegative[c - 1]) {
                        dp[c][0] = dp[c - 1][1] * curNum;
                        isPositive[c] = true;
                    } else if (isNegative[c]) {
                        dp[c][0] = dp[c][1] * curNum;
                        isPositive[c] = true;
                    } else {
                        isPositive[c] = false;
                    }

                    if (isPositive[c - 1] && isPositive[c]) {
                        dp[c][1] = Math.min(dp[c - 1][0] * curNum, dp[c][0] * curNum);
                        isNegative[c] = true;
                    } else if (isPositive[c - 1]) {
                        dp[c][1] = dp[c - 1][0] * curNum;
                        isNegative[c] = true;
                    } else if (isPositive[c]) {
                        dp[c][1] = dp[c][0] * curNum;
                        isNegative[c] = true;
                    } else {
                        isNegative[c] = false;
                    }
                }
            }
        }
        return (int) (dp[col][0] % (1e9 + 7));
    }
}


