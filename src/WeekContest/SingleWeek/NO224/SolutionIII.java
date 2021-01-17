package WeekContest.SingleWeek.NO224;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/17 10:54
 * @description
 * [[1,1,0,0,1,0,1,1,0,1,1,1,1,0,1,1,1,0,1,1,1,0,1,0,0,1,1,1,1,1,0,1,0,1,1],
 *  [1,1,1,1,1,1,0,1,0,1,1,1,0,1,1,1,1,0,1,1,0,1,1,1,1,1,1,0,1,0,0,1,1,1,1],
 *  [1,1,1,0,1,1,1,1,1,0,0,0,1,1,1,0,1,1,1,1,1,1,1,1,1,0,1,1,1,0,1,1,1,1,0]]
 * 42
 * 40
 */
public class SolutionIII {

    public static void main(String[] args) {
        System.out.println(new SolutionIII().largestSubmatrix(new int[][]{
                {1,1,0,0,1,0,1,1,0,1,1,1,1,0,1,1,1,0,1,1,1,0,1,0,0,1,1,1,1,1,0,1,0,1,1},
                {1,1,1,1,1,1,0,1,0,1,1,1,0,1,1,1,1,0,1,1,0,1,1,1,1,1,1,0,1,0,0,1,1,1,1},
                {1,1,1,0,1,1,1,1,1,0,0,0,1,1,1,0,1,1,1,1,1,1,1,1,1,0,1,1,1,0,1,1,1,1,0}}));
    }

    public int largestSubmatrix(int[][] matrix) {
        int col = matrix[0].length;
        int[] dp = new int[col];
        int res = 0;
        for (int[] ints : matrix) {
            for (int j = 0; j < col; j++) {
                dp[j] = ints[j] == 0 ? 0 : dp[j] + 1;
            }
                int[] copy = Arrays.copyOf(dp, dp.length);
                Arrays.sort(copy);
                for (int k = copy.length - 1; k >= 0; k--) {
                    res = Math.max(copy[k] * (copy.length - k), res);
                }

        }
        return res;
    }

}
