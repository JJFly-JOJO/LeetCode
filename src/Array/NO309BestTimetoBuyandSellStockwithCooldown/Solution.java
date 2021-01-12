package Array.NO309BestTimetoBuyandSellStockwithCooldown;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/12 22:59
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 */
public class Solution {

    /**
     * 当前i天买入（第i天能够买入） 那么第i-1天一定不可能卖出 因为卖出的话 那么第i天是被冷冻的
     * 由于没有限定买卖次数 那么我们子问题的k上限值就为length/2
     * 思考：如果不要k（买卖次数状态） 能否找到一个满足无后效性的子问题？
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int length = prices.length;
        //由于不限制交易次数 因此k设置为最大上限值
        int k = length / 2;
        int[][] dp = new int[length + 1][k + 1];
        for (int j = 1; j <= k; j++) {
            int min = prices[0];
            for (int i = 2; i <= length; i++) {
                if (i > 2) {
                    min = Math.min(min, prices[i - 2] - dp[i - 3][j - 1]);
                }
                dp[i][j] = Math.max(dp[i - 1][j], prices[i - 1] - min);
            }
        }
        return dp[length][k];
    }

    /**
     * 优化一：内层循环减少一次if条件判断
     *
     * @param prices
     * @return
     */
    public int maxProfitOp(int[] prices) {
        int length = prices.length;
        if (length < 2) {
            return 0;
        }
        //由于不限制交易次数 因此k设置为最大上限值
        int k = length / 2;
        int minVal = prices[1] - prices[0];
        int[][] dp = new int[length + 1][k + 1];
        //initialize
        if (minVal > 0) {
            for (int i = 1; i <= k; i++) {
                dp[2][i] = minVal;
            }
        }
        for (int j = 1; j <= k; j++) {
            int min = prices[0];
            for (int i = 3; i <= length; i++) {
                min = Math.min(min, prices[i - 2] - dp[i - 3][j - 1]);
                dp[i][j] = Math.max(dp[i - 1][j], prices[i - 1] - min);
            }
        }
        return dp[length][k];
    }

    /**
     * 由于买卖天数没有限制 因此不需要买卖次数k的状态 使用一维dp即可
     * @param prices
     * @return
     */
    public int maxProfitOneDemensionDp(int[] prices) {
        int length = prices.length;
        if (length < 2) {
            return 0;
        }
        int[] dp = new int[length + 1];
        //initialize
        if (prices[1] - prices[0] > 0) {
            dp[2] = prices[1] - prices[0];
        }
        int min = prices[0];
        for (int i = 3; i <= length; i++) {
            min = Math.min(min, prices[i - 2] - dp[i - 3]);
            //第i天卖出的情况全部枚举后 找到了最值 还要与第i天不卖出的情况做比较
            //dp[i-1]表明了第i天是不卖出的
            dp[i] = Math.max(dp[i - 1], prices[i - 1] - min);
        }
        return dp[length];
    }

}
