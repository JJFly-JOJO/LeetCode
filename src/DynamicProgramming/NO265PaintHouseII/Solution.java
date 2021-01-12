package DynamicProgramming.NO265PaintHouseII;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/4 12:24
 * @description
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().minCostII(new int[][]{{8}}));
    }

    /**
     * ---------------注意 还可以进行状态压缩--------
     * @param costs
     * @return
     */
    public int minCostII(int[][] costs) {
        int num;
        int colorNum;
        if ((num = costs.length) == 0 || (colorNum = costs[0].length) == 0) {
            return 0;
        }
        //特判 [[8]]
        if (colorNum == 1) {
            return costs[0][0];
        }
        int[][] dp = new int[num + 1][colorNum];
        for (int i = 1; i <= num; i++) {
            //只记录最小 第二小的前一子问题 以及最小成本对应的颜色
            //优化---------------------------这里可以只用三个int型变量 new数组以及调用Arrays方法效率低
            int[] arr = new int[3];
            Arrays.fill(arr, Integer.MAX_VALUE);
            //当前子问题枚举前 先更新好上一次子问题的第一小 第二小成本 以及对应index
            for (int k = 0; k < colorNum; k++) {
                if (dp[i - 1][k] < arr[0]) {
                    int t = arr[0];
                    arr[0] = dp[i - 1][k];
                    arr[2] = k;
                    arr[1] = t;
                } else {
                    if (dp[i - 1][k] < arr[1]) {
                        arr[1] = dp[i - 1][k];
                    }
                }
            }
            for (int j = 0; j < colorNum; j++) {
                if (j == arr[2]) {
                    dp[i][j] = arr[1] + costs[i - 1][j];
                } else {
                    dp[i][j] = arr[0] + costs[i - 1][j];
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int c : dp[num]) {
            if (c < res) {
                res = c;
            }
        }
        return res;
    }

}
