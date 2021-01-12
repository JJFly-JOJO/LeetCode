package DynamicProgramming.NO221MaximalSquare;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/17 9:48
 * @description ------------------类似NO1277--------------
 * 正方形矩阵右下角下标ij 以及正方形边长唯一确定一个正方形矩阵
 */
public class SolutionII {

    public int maximalSquare(char[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row][col];
        int res = 0;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (matrix[r][c] == '1') {
                    if (r > 0 && c > 0) {
                        dp[r][c] = Math.min(dp[r - 1][c - 1], Math.min(dp[r - 1][c], dp[r][c - 1])) + 1;
                    } else {
                        dp[r][c] = 1;
                    }
                    res = Math.max(res, dp[r][c]);
                }
            }
        }
        return res * res;
    }
}
