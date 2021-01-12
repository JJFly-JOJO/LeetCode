package DynamicProgramming.NO198HouseRobber;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/22 11:44
 * @description --------------动态规划---------------
 * 思路：并不需要细分前一个子问题（dp[i-1]）是否已经选择了nums[i-1]元素造成当前nums[i]是否可选，只需要从
 * dp[i-2]--->当前i元素可选 dp[i-1]--->当前元素不可选 两种情况找最优
 */
public class Solution {

    /**
     * 状态压缩：可以发现我们每次dp只用到了dp[i-1]dp[i-2]，因此我们完全可以只使用两个变量a b（滚动数组 利用temp实现滚动）
     * 来代替dp数组
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[1], nums[0]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return Math.max(dp[nums.length - 1], dp[nums.length - 2]);
    }
}
