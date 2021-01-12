package DynamicProgramming.NO85MaximalRectangle;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/21 11:41
 * @description
 */
public class Solution {

    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int col = matrix[0].length;
        int[] dp = new int[col];
        int max = 0;
        for (char[] chars : matrix) {
            for (int j = 0; j < col; j++) {
                dp[j] = chars[j] == '1' ? dp[j] + 1 : 0;
            }
            max = Math.max(max, getLargersRectangle(dp));
        }
        return max;
    }

    private int getLargersRectangle(int[] dp) {
        int l = dp.length;
        int[] left = new int[l];
        int[] right = new int[l];
        int max = 0;
        Arrays.fill(right, l);
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < l; i++) {
            while (!stack.isEmpty() && dp[i] <= dp[stack.peek()]) {
                right[stack.pop()] = i;
            }
            left[i] = stack.isEmpty() ? 0 : stack.peek() + 1;
            stack.push(i);
        }
        for (int i = 0; i < dp.length; i++) {
            max = Math.max(max, dp[i] * (right[i] - left[i]));
        }
        return max;
    }

}
