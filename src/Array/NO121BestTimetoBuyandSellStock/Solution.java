package Array.NO121BestTimetoBuyandSellStock;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/10 15:33
 */
public class Solution {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 0, 1, 2};
        System.out.println(new Solution().maxProfit(nums));
    }

    public int maxProfit(int[] prices) {
        int length = prices.length;
        if (length == 0) {
            return 0;
        }
        int minNum = prices[0];
        int maxNum = 0;
        int res = 0;
        for (int i = 1; i < length; i++) {
            if (prices[i] < minNum) {
                maxNum = maxNum - (minNum - prices[i]);
                minNum = prices[i];
            } else if (prices[i] > maxNum && prices[i] > minNum) {
                maxNum = prices[i];
                res = maxNum - minNum;
            }
        }
        return res;
    }

}
