package Matrix.NO74Searcha2DMatrix;

public class Solution {

    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        if (row == 0) {
            return false;
        }
        int col = matrix[0].length;
        if (col == 0) {
            return false;
        }
        int startRow = 0;
        for (; startRow < row; startRow++) {
            if (matrix[startRow][0] == target) {
                return true;
            } else if (matrix[startRow][0] > target) {
                break;
            }
        }
        startRow--;
        if (startRow < 0) {
            //target比第一行的第一个数还小
            return false;
        }
        if (target > matrix[startRow][col - 1]) {
            return false;
        }
        //二分查找
        int left = 0;
        int right = col - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (matrix[startRow][mid] < target) {
                left = mid + 1;
            } else if (matrix[startRow][mid] > target) {
                right = mid;
            } else {
                return true;
            }
        }
        return matrix[startRow][left] == target;
    }

}
