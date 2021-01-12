package DynamicProgramming.NO64MinimumPathSum;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/29 16:37
 * m x n矩阵元素都是非负整数
 * 输入:
 * [
 * [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 */
public class Solution {

    public int minPathSum(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int[] dp1 = new int[col + 1];
        int[] dp2 = new int[col + 1];
        //initialize
        Arrays.fill(dp1, Integer.MAX_VALUE);
        //control
        int button = 1;
        for (int r = 1; r <= row; r++) {
            for (int c = 1; c <= col; c++) {
                if (button == 1) {
                    //易错点！！！！！！！不要让dp[]先加grid[][]再Math.min 会因为Integer.MAX_VALUE造成溢出
                    dp2[c] = Math.min(dp2[c - 1], dp1[c]) + grid[r - 1][c - 1];
                } else {
                    dp1[c] = Math.min(dp1[c - 1], dp2[c]) + grid[r - 1][c - 1];
                }
            }
            button ^= 1;
            dp2[0]=Integer.MAX_VALUE;
        }
        return row % 2 == 1 ? dp2[col] : dp1[col];
    }

}
