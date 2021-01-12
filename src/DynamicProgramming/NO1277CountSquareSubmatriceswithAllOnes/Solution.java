package DynamicProgramming.NO1277CountSquareSubmatriceswithAllOnes;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/17 10:31
 * @description --------------类似221--------------
 */
public class Solution {

    public int countSquares(int[][] matrix) {
        //正方形矩阵右下角下标ij 以及正方形边长唯一确定一个正方形矩阵
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row][col];
        int res = 0;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (matrix[r][c] == 1) {
                    if (r > 0 && c > 0) {
                        dp[r][c] = Math.min(dp[r - 1][c - 1], Math.min(dp[r - 1][c], dp[r][c - 1])) + 1;
                    } else {
                        dp[r][c] = 1;
                    }
                    res += dp[r][c];
                }
            }
        }
        return res;
    }
}
