package Array.NO327CountofRangeSum;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/13 14:43
 * 输入: nums = [-2,5,-1], lower = -2, upper = 2,
 * 输出: 3
 * 解释: 3个区间分别是: [0,0], [2,2], [0,2]，它们表示的和分别为: -2, -1, 2。
 * ------------------low<=sum[j]-sum[i]<=up 类似动态规划思想-------------
 */
public class Solution {

    /**
     * long dp[][]存在内存限制 状态压缩----------------此dp也可以看出我们只需要前缀数组就能求之后的数组
     *
     * @param nums
     * @param lower
     * @param upper
     * @return
     */
    public int countRangeSum(int[] nums, int lower, int upper) {
        //[-2,5,-1]
        //-2
        //2
        int length = nums.length;
        int count = 0;
        long[] dp1 = new long[length];
        long[] dp2 = new long[length];
        //initialize
        for (int i = 0; i < length; i++) {
            dp1[i] = nums[i];
            if (nums[i] <= upper && nums[i] >= lower) {
                count++;
            }
        }
        int button = 1;
        for (int i = 1; i < length; i++) {
            int lastIndex = length - i;
            for (int j = 0; j < lastIndex; j++) {
                /*int ji = j + i;
                dp[j][ji] = dp[j][ji - 1] + (long) nums[ji];
                if (dp[j][ji] >= lower && dp[j][ji] <= upper) {
                    count++;
                }*/
                if (button == 1) {
                    dp2[j] = dp1[j] + (long) nums[j + i];
                    if (dp2[j] >= lower && dp2[j] <= upper) {
                        count++;
                    }
                } else {
                    dp1[j] = dp2[j] + (long) nums[j + i];
                    if (dp1[j] >= lower && dp1[j] <= upper) {
                        count++;
                    }
                }
            }
            button ^= 1;
        }
        return count;
    }

}
