package Matrix.NO54SpiralMatrix;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 2, 3}, {5, 6, 7}, {9, 10, 11}, {12, 13, 14}};
        List<Integer> result = new Solution().spiralOrder(matrix);
        System.out.println(result);
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0)
            return result;
        //一圈一圈输出
        int m = matrix.length;//row
        int n = matrix[0].length;//col
        int curRow = 0;
        int curCol = 0;
        // int rowNum = m;
        // int colNum = n;
        int endRow = m - 1;
        int endCol = n - 1;
        //记录矩阵对角

        //到达中间点 停止
        while (curRow <= endRow && curCol <= endCol) {

            for (int j = curCol; j <= endCol; j++) {
                result.add(matrix[curRow][j]);
            }
            //针对单行多列的矩阵 防止重复遍历
            //1->2->3->4 4->3->2->1
            if (curRow + 1 > endRow)
                break;
            for (int j = curRow + 1; j <= endRow; j++) {
                result.add(matrix[j][endCol]);
            }
            //针对多行单列的矩阵 防止重复遍历
            if (endCol - 1 < curCol)
                break;
            for (int j = endCol - 1; j >= curCol; j--) {
                result.add(matrix[endRow][j]);
            }
            for (int j = endRow - 1; j > curRow; j--) {
                result.add(matrix[j][curCol]);
            }
            curRow++;
            curCol++;
            endRow--;
            endCol--;
            //rowNum = rowNum - 2 + curRow;
            //colNum = colNum - 2 + curCol;
        }
        return result;
    }
}
