package DynamicProgramming.NO85MaximalRectangle;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/21 14:56
 * @description --------------------height left right三个dp的动态规划-------------------
 */
public class SolutionII {

    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int max = 0;
        int col = matrix[0].length;
        int[] height = new int[col];
        int[] left = new int[col];
        int[] right = new int[col];
        Arrays.fill(right, col);
        for (char[] r : matrix) {
            int curL = 0;
            int curR = col;
            //dp height
            for (int i = 0; i < col; i++) {
                height[i] = r[i] == '1' ? height[i] + 1 : 0;
            }
            //dp left
            for (int i = 0; i < col; i++) {
                if (r[i] == '1') {
                    left[i] = Math.max(left[i], curL);
                } else {
                    //reset
                    left[i] = 0;
                    curL = i + 1;
                }
            }
            //dp right
            for (int i = col - 1; i >= 0; i--) {
                if (r[i] == '1') {
                    right[i] = Math.min(right[i], curR);
                } else {
                    //reset
                    right[i] = col;
                    curR = i;
                }
            }
            //calculate area
            for (int i = 0; i < col; i++) {
                max = Math.max(max, (right[i] - left[i]) * height[i]);
            }
        }
        return max;
    }

}
