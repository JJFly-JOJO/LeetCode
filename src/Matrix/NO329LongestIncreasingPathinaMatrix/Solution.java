package Matrix.NO329LongestIncreasingPathinaMatrix;

public class Solution {

    private int row;

    private int col;

    private int max = 0;

    public int longestIncreasingPath(int[][] matrix) {
        row = matrix.length;
        if (row == 0) {
            return 0;
        }
        col = matrix[0].length;
        int[][] res = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                DFS(i, j, res, matrix);
            }
        }
        return max;
    }

    /**
     * @param i   row
     * @param j   column
     * @param res
     */
    private int DFS(int i, int j, int[][] res, int[][] matrix) {
        if (res[i][j] != 0) {
            return res[i][j];
        }
        if (i - 1 >= 0 && matrix[i - 1][j] > matrix[i][j]) {
            //上
            res[i][j] = Math.max(res[i][j], DFS(i - 1, j, res, matrix) + 1);
        }
        if (i + 1 < row && matrix[i + 1][j] > matrix[i][j]) {
            //下
            res[i][j] = Math.max(res[i][j], DFS(i + 1, j, res, matrix) + 1);
        }
        if (j - 1 >= 0 && matrix[i][j - 1] > matrix[i][j]) {
            //左
            res[i][j] = Math.max(res[i][j], DFS(i, j - 1, res, matrix) + 1);
        }
        if (j + 1 < col && matrix[i][j + 1] > matrix[i][j]) {
            //右
            res[i][j] = Math.max(res[i][j], DFS(i, j + 1, res, matrix) + 1);
        }
        //当前元素是最小元素
        res[i][j] = Math.max(res[i][j], 1);
        max = Math.max(res[i][j], max);
        return res[i][j];
    }

}
