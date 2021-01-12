package DynamicProgramming.NO322CoinChange;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/28 14:46
 * <p>
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * <p>
 * 输入: coins = [2], amount = 3
 * 输出: -1
 *
 *
 * ---------------另一种方法 DFS------------
 */
public class Solution {

    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int length = coins.length;
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            int count = Integer.MAX_VALUE;
            for (int j = 0; j < length; j++) {
                int temp = i - coins[j];
                if (temp < 0) {
                    break;
                } else {
                    if (dp[temp] != Integer.MAX_VALUE) {
                        count = Math.min(dp[temp] + 1, count);
                    }
                }
            }
            dp[i] = count;
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

}
