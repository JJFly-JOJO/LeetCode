package Backtracking.NO377CombinationSumIV;

import java.util.Arrays;

/**
 * 动态规划适用于直接输出状态值结果 而不需要具体结果组成的路径情况
 *
 * 思考：负数情况：如果存在负数 会出现 target=4 [4，-4] 会出现无数解的情况（dp此种情况的状态值不仅限于dp[4]
 * 还存在dp[8+(-4)] dp[12+(-4)+(-4)]）等等
 * 排除负数情况的方法：
 * 两数相减一定不能等于0 否则组合结果无数个
 * 负数限定使用次数
 */
public class Solution {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 1, 3};
        System.out.println(new Solution().combinationSum4(nums, 35));
    }

    /**
     * 回溯算法：此方法存在超时的情况 因为有很多重复路径的搜索
     *
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);
        int res = 0;
        int curSum = 0;
        for (int i = 0; i < nums.length; i++) {
            curSum = nums[i];
            if (curSum < target) {
                res = BackTracking(nums, target, curSum, res);
            } else if (curSum == target) {
                res++;
                break;
            } else {
                break;
            }
        }
        return res;
    }

    private int BackTracking(int[] nums, int target, int curSum, int res) {
        for (int i = 0; i < nums.length; i++) {
            int tempSum = curSum + nums[i];
            if (tempSum < target) {
                res = BackTracking(nums, target, tempSum, res);
            } else if (tempSum == target) {
                return ++res;
            } else {
                return res;
            }
        }
        return res;
    }

    /**
     * 回顾回溯方法会超时的原因：对于一种结果 例如[1,2,3]（7） 会有重复的组合[2,1,3] [3,2,1]等 也就是说
     * 回溯方法会有很多重复的结果 并且此题并不要求将具体结果记录 只需要得到结果数
     * 因此可以考虑动态规划
     * 动态规划方法：关键状态是谁 状态转移方程如何表达
     * 首先我们思考能否用问题所问 直接作为状态，这道题是问target的组成个数 我们很容易看出target是可以
     * 由子target1 target2..组合而成 因此我们的dp以target：1,2,3，...target作为状态
     * 注意我们的输入都是正整数 target也为正整数
     * （1）状态转移方程如何表示：
     * 我们以target=7 [1,3,4]举例：7=1+dp[6] 7=3+dp[4] 7=4+dp[3]
     * （2）初始状态dp[0]=1的原因：
     * 如果target=4 [1,3,4]那么有 4=4+dp[0] 可见 dp[0]我们应该设置为1 也可以理解为 dp[0]映射的是元素集合中存在
     * 元素值与target值相同的情况
     * 如果有target=4 [5,6,7] 那么自然最终返回值dp[4]为0 dp[0]的值并不会有错误影响
     *
     * @param nums   数组内的元素全部为正整数
     * @param target
     * @return
     */
    public int combinationSum4ForDP(int[] nums, int target) {
        //target作为状态值 从0到target
        int[] dp = new int[target + 1];
        //初始状态dp[0]初始化
        dp[0] = 1;
        //外层控制子问题规模
        for (int t = 1; t <= target; t++) {
            //子问题枚举
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= t) {
                    dp[t] += dp[t - nums[i]];
                }
            }
        }
        return dp[target];
    }

}
