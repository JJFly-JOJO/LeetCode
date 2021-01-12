package Array.NO45JumpGameII;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/10 13:57
 */
public class Solution {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        System.out.println(new Solution().jumpForGreedy(nums));
    }

    /**
     * 解法一：动态规划（一维）
     *
     * @param nums
     * @return
     */
    public int jumpForDP(int[] nums) {
        int length = nums.length;
        //dp
        int[] dp = new int[length + 1];
        //initialize
        dp[0] = 0;
        dp[1] = 0;
        for (int i = length; i > 1; i--) {
            dp[i] = Integer.MAX_VALUE;
        }
        //dp[1]=1;

        //子问题规模
        for (int i = 2; i <= length; i++) {
            int lastIndex = i - 1;
            //子问题求解枚举
            for (int j = 1; j < i; j++) {
                if (dp[i] == 1) {
                    //dp[i]=1已经是最优解
                    break;
                }
                //状态转移 如果能够达到规模为i的子问题 那么就比较 取最小值
                //首先当前dp[i]要能够达到 也就是说 不等于Integer.MAX_VALUE
                if (dp[j] != Integer.MAX_VALUE && j - 1 + nums[j - 1] >= lastIndex) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[length];
    }

    /**
     * 贪心算法---正向
     * 反向贪心思路： 从左到右循环 遇到的第一个能到达lastIndex的点 更新当前点为lastIndex
     * 继续从左到右循环 遇到的第一个能到达lastIndex的点...
     *
     * @param nums
     * @return
     */
    public int jumpForGreedy(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return 0;
        }
        int lastIndex = length - 1;
        int left = 0;
        int right = nums[0] + 0;
        int count = 0;
        while (right < lastIndex) {
            int tempMaxRight = right;
            for (int i = left + 1; i <= right; i++) {
                int tempVal = i + nums[i];
                if (tempVal > tempMaxRight) {
                    tempMaxRight = tempVal;
                }
            }
            left = right;
            right = tempMaxRight;
            count++;
        }
        return ++count;
    }

}
