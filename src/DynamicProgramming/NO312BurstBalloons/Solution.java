package DynamicProgramming.NO312BurstBalloons;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 1, 5, 8};
        System.out.println(new Solution().maxCoinsForDivide(nums));
    }

    public int maxCoins(int[] nums) {
        //加上首位1
        int lengthNew = nums.length + 2;
        int[] balloons = new int[lengthNew];
        balloons[0] = 1;
        balloons[lengthNew - 1] = 1;
        for (int i = 1; i < lengthNew - 1; i++) {
            balloons[i] = nums[i - 1];
        }
        int length = nums.length;
        int[][] dp = new int[length + 2][length + 2];
        //initialize
        dp[0][0] = 1;
        dp[length + 1][length + 1] = 1;
        //状态转移动作（当前操作）------>最后一个戳破的气球！！！！！！！
        for (int i = 1; i <= length; i++) {
            int sonIndex = length - i + 1;
            for (int j = 1; j <= sonIndex; j++) {
                int lastOne = j + i - 1;
                int maxCoin = Integer.MIN_VALUE;
                //枚举k
                for (int k = j; k <= lastOne; k++) {
                    int res = dp[j][k - 1] + dp[k + 1][lastOne] + balloons[k] * balloons[j - 1] * balloons[lastOne + 1];
                    if (res > maxCoin) {
                        maxCoin = res;
                        dp[j][lastOne] = res;
                    }
                }
            }
        }
        return dp[1][length];
    }

    /**
     * 带有记忆的分治策略-自顶向下（从总体问题分解成子问题）
     *
     * @param nums
     * @return
     */
    public int maxCoinsForDivide(int[] nums) {
        int length = nums.length;
        int[] balloons = new int[length + 2];
        balloons[0] = 1;
        balloons[length + 1] = 1;
        for (int i = 0; i < length; i++) {
            balloons[i + 1] = nums[i];
        }
        //缓存子问题结果集
        int[][] res = new int[length + 2][length + 2];
        for (int i = 0; i < length + 1; i++) {
            //数组快速初始化<------------------------
            Arrays.fill(res[i], 0);
        }

        return Divide(0, length + 1, res, balloons);
    }

    private int Divide(int left, int right, int[][] res, int[] balloons) {
        /*if (left-right==1) {
            return 0;
        }*/
        if (res[left][right] != 0) {
            return res[left][right];
        }

        //当前层枚举
        for (int i = left + 1; i <= right - 1; i++) {
            //最后一次戳的i 那么左右剩下的是left与right边界 例如[(1) 3 5 3 6 (1)]
            //最后一次戳5 那么左边和右边都戳完了 那么与左边界1 右边界1相乘
            int tempRes = balloons[i] * balloons[left] * balloons[right]
                    + Divide(left, i, res, balloons)
                    + Divide(i, right, res, balloons);
            if (tempRes > res[left][right]) {
                res[left][right] = tempRes;
            }
        }
        return res[left][right];
    }

}
