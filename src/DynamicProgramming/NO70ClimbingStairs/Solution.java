package DynamicProgramming.NO70ClimbingStairs;

public class Solution {
    //基于递归思路的动态规划
    //动态规划本质是当前循环所的到的结果是根据之前的结果而得到的
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {//不用i<n+1 目的是减少每次循环的运算
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
