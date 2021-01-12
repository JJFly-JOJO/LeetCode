package WeekContest.DoubleWeek.NO36;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/3 22:50
 * 输入：rowSum = [3,8], colSum = [4,7]
 * 输出：[[3,0],
 * [1,7]]
 * 解释：
 * 第 0 行：3 + 0 = 3 == rowSum[0]
 * 第 1 行：1 + 7 = 8 == rowSum[1]
 * 第 0 列：3 + 1 = 4 == colSum[0]
 * 第 1 列：0 + 7 = 7 == colSum[1]
 * 行和列的和都满足题目要求，且所有矩阵元素都是非负的。
 * 另一个可行的矩阵为：[[1,2],
 * [3,5]]
 */
public class Solution3 {

    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int row = rowSum.length;
        int col = colSum.length;
        int[][] res = new int[row][col];
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                int tempVal = Math.min(rowSum[r], colSum[c]);
                res[r][c] = tempVal;
                rowSum[r] -= tempVal;
                colSum[c] -= tempVal;
            }
        }
        return res;
    }

}
