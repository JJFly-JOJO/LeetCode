package DynamicProgramming.NO363MaxSumofRectangleNoLargerThanK;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/22 10:59
 * @description --------------优化的暴力枚举--------------
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().maxSumSubmatrix(new int[][]{{5, -4, -3, 4}, {-3, -4, 4, 5}, {5, 1, 5, -4}}, 10));
        String s="1";
        s.replace(".", "/");
    }

    public int maxSumSubmatrix(int[][] matrix, int k) {
        int row = matrix.length;
        int col = matrix[0].length;
        int max = Integer.MIN_VALUE;
        //以列为边界枚举
        for (int l = 0; l < col; l++) {
            //记录当前左边界右边界下的一行的和
            int[] subSum = new int[row];
            for (int r = l; r < col; r++) {
                for (int i = 0; i < row; i++) {
                    subSum[i] += matrix[i][r];
                }
                //暴力求解当前边界下的所有子矩阵和的最大值
                max = Math.max(max, getMaxRectangleSum(subSum, k));
            }
        }
        return max;
    }

    /**
     * 贪心算法求解最大值
     */
    private int getMaxRectangleSum(int[] subSum, int k) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        //O(n)遍历找到最大
        for (int value : subSum) {
            sum += value;
            max = Math.max(sum, max);
            //sum小于0 重置为0
            sum = Math.max(sum, 0);
        }
        //当前获得的最大值是全局最大 该值与k比较 看是否满足要求
        if (max <= k) {
            return max;
        }
        //O(n2)找最大且小于k
        max = Integer.MIN_VALUE;
        for (int i = 0; i < subSum.length; i++) {
            sum = 0;
            for (int j = i; j < subSum.length; j++) {
                sum += subSum[j];
                if (sum <= k && sum > max) {
                    max = sum;
                }
                if (max == k) {
                    return max;
                }
            }
        }
        return max;
    }

}
