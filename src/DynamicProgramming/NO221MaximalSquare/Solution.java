package DynamicProgramming.NO221MaximalSquare;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/15 16:35
 * @description
 */
public class Solution {

    public static void main(String[] args) {

    }

    public int maximalSquare(char[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int n = Math.min(row, col);
        int res = 0;
        int tag = 0;
        boolean[][][] dp = new boolean[row][col][n + 1];
        //init
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (matrix[r][c] == '1') {
                    dp[r][c][1] = true;
                    tag = 1;
                }
            }
        }
        if (tag == res) {
            return res;
        }
        res = tag;
        for (int s = 2; s <= n; s++) {
            for (int r = s - 1; r < row; r++) {
                for (int c = 0; c <= col - s; c++) {
                    if (matrix[r][c] == '1' && dp[r - 1][c][s - 1] && dp[r][c + 1][s - 1] && dp[r - 1][c + 1][s - 1]) {
                        tag = s;
                        dp[r][c][s] = true;
                    }
                }
            }
            if (tag == res) {
                return res * res;
            }
            res = tag;
        }
        return res * res;
    }
}
