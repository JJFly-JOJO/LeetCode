package Array.NO122BestTimetoBuyandSellStockII;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/11 13:42
 */
public class Solution {

    /**
     * 函数的单调区间 贪心算法
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int length = prices.length;
        int profit = 0;
        for (int i = 1; i < length; i++) {
            int val = prices[i] - prices[i - 1];
            if (val > 0) {
                profit += val;
            }
        }
        return profit;
    }
}
