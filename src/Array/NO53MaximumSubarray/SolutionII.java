package Array.NO53MaximumSubarray;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/18 15:42
 * @description ------------动态规划解法 联系NO152------------
 * 思路：我们要抓住连续子数组关键，既然我们要找最大的子数组，我们以数组右边界作为枚举点，我们枚举每一个数组元素
 * 作为子数组的右边界的最大连续和，最终结果为每个元素作为右边界对应的最大值中找出最大值--------子问题我们就划分好了
 * （我们用暴力枚举的思路来看：就是每个元素作为右边界时，左边界指针移动枚举所有可能子区间的情况）
 * 状态转移：当前元素i作为右边界下的最值有两种情况：dp[i-1]+nums[i]或者nums[i]中选择最大的那个数
 * （因为要保证连续子区间 dp是不可能取到dp[i-2]的）
 * <p>
 * <p>
 * 输入: [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大为 6。
 */
public class SolutionII {

    public int maxSubArray(int[] nums) {
        int len = nums.length;
        int res = Integer.MIN_VALUE;
        int[] dp = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i - 1], nums[i - 1]);
            res = Math.max(dp[i], res);
        }
        return res;
    }

}
