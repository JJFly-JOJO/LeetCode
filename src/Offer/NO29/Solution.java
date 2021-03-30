package Offer.NO29;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/2/19 0:08
 * @description
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().spiralOrder(new int[][]{{1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {9}, {10}})));
    }

    public int[] spiralOrder(int[][] matrix) {
        int row = 0;
        int col = 0;
        if ((row = matrix.length) == 0 || (col = matrix[0].length) == 0) {
            return new int[0];
        }
        int[] res = new int[row * col];
        int index = 0;
        int top = 0;
        int bottom = row - 1;
        int left = 0;
        int right = col - 1;
        while (top <= bottom && left <= right) {
            for (int i = left; i <= right; i++) {
                res[index++] = matrix[top][i];
            }
            for (int i = top + 1; i <= bottom; i++) {
                res[index++] = matrix[i][right];
            }
            if (left < right && top < bottom) {
                for (int i = right - 1; i > left; i--) {
                    res[index++] = matrix[bottom][i];
                }
                for (int i = bottom; i > top; i--) {
                    res[index++] = matrix[i][left];
                }
            }
            top++;
            bottom--;
            left++;
            right--;
        }
        return res;
    }

}
