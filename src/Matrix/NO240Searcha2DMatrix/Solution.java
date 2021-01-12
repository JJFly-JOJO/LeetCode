package Matrix.NO240Searcha2DMatrix;

/**
 * ------------笔记---------
 *
 */
public class Solution {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        System.out.println(new Solution().searchMatrix(matrix, 5));
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        if (row == 0) {
            return false;
        }
        int col = matrix[0].length;
        if (col == 0) {
            return false;
        }
        int valOfTarget = getVal(matrix, target);
        int valOfLast = getVal(matrix, target - 1);
        if (valOfLast != valOfTarget) {
            return true;
        }
        return false;
    }

    private int getVal(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        int res = 0;
        int rowStart = row - 1;
        for (int i = 0; i < col; i++) {
            while (rowStart >= 0) {
                if (matrix[rowStart][i] <= target) {
                    res += rowStart + 1;
                    break;
                }
                rowStart--;
            }
        }
        return res;
    }

    /**
     * 还是依据小于某个值的元素在矩阵左上角 大于某个值的元素在矩阵右下角（本质是右下元素值一定大于左上）
     * 我们只需要从左下角往右上角走一次 如果能走到相等情况 那么自然就找到 没有就是没有找到
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrixBetter(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int rowIndex = row - 1;
        int colIndex = 0;
        //对比上一种解法 我们一个while循环就可以替代两层的循环
        while (rowIndex >= 0 && colIndex < col) {
            if (matrix[rowIndex][colIndex] > target) {
                rowIndex--;
            } else if (matrix[rowIndex][colIndex] < target) {
                colIndex++;
            } else {
                return true;
            }
        }
        return false;
    }


}
