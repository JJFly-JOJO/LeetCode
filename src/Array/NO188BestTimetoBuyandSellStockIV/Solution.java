package Array.NO188BestTimetoBuyandSellStockIV;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/12 18:12
 *
 * -------------------------------动态规划--------子问题的无后效性（最优原则）（最优子结构性质）-----------------
 * 优化原则（最优子结构性质）：一个最优决策序列的任何子序列本身一定是相对于子序列的初始和结束状态的最优决策序列
 * 无后效性：（1）当前做的决策一旦确定，后面的决策不会影响到之前决策的结果；
 *          （2）当前决策得到一个最优值，这个最优值怎么来的，后面的决策并不关心。
 * 消除后效性的方法：增加dp[][]..维度 也就是多设置一个状态消除后效性
 * 总之，子问题的最优解一定是针对子问题的全局最优解 不受其他情况影响
 */
public class Solution {

    public static void main(String[] args) {
        int[] prices = new int[]{3, 2, 6, 5, 0, 3};
        System.out.println(new Solution().maxProfitSpaceOp(6, prices));
    }

    /**
     * 二维dp
     * 空间未优化 超出内存限制
     *
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices) {
        int length = prices.length;
        if (length == 0) {
            return 0;
        }
        int[][] dp = new int[length + 1][k + 1];
        for (int j = 1; j <= k; j++) {
            int min = prices[0];
            for (int i = 2; i <= length; i++) {
                min = Math.min(min, prices[i - 2] - dp[i - 2][j - 1]);
                dp[i][j] = Math.max(dp[i - 1][j], prices[i - 1] - min);
            }
        }
        return dp[length][k];
    }

    /**
     * 由于j % 2 == 0 ? 1 : 0 会存在超时情况
     *
     * @param k
     * @param prices
     * @return
     */
    public int maxProfitSpaceOp(int k, int[] prices) {
        int length = prices.length;
        if (length == 0) {
            return 0;
        }
        int[][] dp = new int[2][length + 1];
        for (int j = 1; j <= k; j++) {
            int min = prices[0];
            for (int i = 2; i <= length; i++) {
                min = Math.min(min, prices[i - 2] - dp[j % 2][i - 2]);
                dp[j % 2 == 0 ? 1 : 0][i] = Math.max(dp[j % 2 == 0 ? 1 : 0][i - 1], prices[i - 1] - min);
            }
        }
        return Math.max(dp[0][length], dp[1][length]);
    }

    /**
     * 二维数组效率很差 由于在空间中不是连续的内存（数组个数） 因此效率不如一维数组
     * 同时需要注意：如果空间连续内存不够，那么我们只能用链表代替数组来操作了
     * 超时本质 k的值很大时候 如果k的值大于length/2 那么也就意味着 可以交易任意次数
     * 因为交易的最多次数（当天买 第二天卖）最多交易次数也就是length/2次
     *
     * @param k
     * @param prices
     * @return
     */
    public int maxProfitSpaceOpTimeOp(int k, int[] prices) {
        int length = prices.length;
        if (length == 0) {
            return 0;
        }
        //int[][] dp = new int[2][length + 1];
        int[] dp1 = new int[length + 1];
        int[] dp2 = new int[length + 1];
        int click = 0;
        //k值实际上是有上限的 交易次数最多只能达到length/2次
        if (k > length / 2) {
            k = length / 2;
        }
        //思考：同时 我们需要注意到 如果k超过上限值 那么当到达任意交易次数之后 外层继续循环的话 结果是否会出现问题？
        //注意我们要纠正之前的理解：dp[i][k]的k不是准确值 k也是一个范围值 表示交易次数小于等于k的最大利润（可以从dp[][]的最开始循环看到）
        //dp[i][1]也可以是0 也就是交易0次 以此类推 也就是k层每一次遍历 k-1 k-2的最大利润结果可能覆盖到k层
        //那么 回到这里，如果k超过了上限值，即便dp[i][k]的k已经超过上限，但是表示的最大利润可能任然是k-n层的结果，
        // 也就是说并不会出现k层超过上限值之后，dp的值也在不断增加的情况
        for (int j = 1; j <= k; j++) {
            int min = prices[0];
            if (click == 1) {
                for (int i = 2; i <= length; i++) {
                    min = Math.min(min, prices[i - 2] - dp1[i - 2]);
                    dp2[i] = Math.max(dp2[i - 1], prices[i - 1] - min);
                }
            } else {
                for (int i = 2; i <= length; i++) {
                    min = Math.min(min, prices[i - 2] - dp2[i - 2]);
                    dp1[i] = Math.max(dp1[i - 1], prices[i - 1] - min);
                }
            }
            click ^= 1;
        }
        return Math.max(dp1[length], dp2[length]);
    }

    /**
     * 由上面的k大于length/2的情况 我们分析得出此种情况等价于交易任意次 那么 如果可以交易任意次
     * 我们可以联想到问题II 利用贪心算法 O（n）复杂度 一次遍历 只要后面的数比前面的大我们就计入利润总和中
     *
     * @param k
     * @param prices
     * @return
     */
    public int maxProfitSpaceOpTimeOpGreedy(int k, int[] prices) {
        int length = prices.length;
        if (length == 0) {
            return maxProfitForGreedy(prices);
        }
        //k值实际上是有上限的 交易次数最多只能达到length/2次
        if (k >= length / 2) {
            //如果满足此条件 说明可以交易任意次 调用贪心算法函数
            return maxProfitForGreedy(prices);
        }
        //int[][] dp = new int[2][length + 1];
        int[] dp1 = new int[length + 1];
        int[] dp2 = new int[length + 1];
        int click = 0;
        for (int j = 1; j <= k; j++) {
            int min = prices[0];
            if (click == 1) {
                for (int i = 2; i <= length; i++) {
                    min = Math.min(min, prices[i - 2] - dp1[i - 2]);
                    dp2[i] = Math.max(dp2[i - 1], prices[i - 1] - min);
                }
            } else {
                for (int i = 2; i <= length; i++) {
                    min = Math.min(min, prices[i - 2] - dp2[i - 2]);
                    dp1[i] = Math.max(dp1[i - 1], prices[i - 1] - min);
                }
            }
            click ^= 1;
        }
        return Math.max(dp1[length], dp2[length]);
    }

    /**
     * k达到上限值 直接调用贪心算法
     * @param prices
     * @return
     */
    public int maxProfitForGreedy(int[] prices) {
        int length = prices.length;
        int res = 0;
        for (int i = 1; i < length; i++) {
            int val = prices[i] - prices[i - 1];
            if (val > 0) {
                res += val;
            }
        }
        return res;
    }

}
