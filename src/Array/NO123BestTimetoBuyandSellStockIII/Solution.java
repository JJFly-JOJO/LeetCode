package Array.NO123BestTimetoBuyandSellStockIII;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/9/11 13:56
 * <p>
 * -----------------动态规划的一步一步时间与空间优化过程---------------
 */
public class Solution {

    public static void main(String[] args) {
        int[] prices = new int[]{1,2,3,4,5};
        System.out.println(new Solution().maxProfitOp(prices));
    }

    /**
     * 此问题为二维dp
     * 子问题：这里我们注意到 如果我们仅仅是将天数来划分子问题（前i天的最大利润）往往是不够的，因为
     * 这里题目还设定了最多买卖两次，也就是说该dp是一个二维dp，子问题为前i天买卖k次的最大利润，
     * 因为上一层的子问题解由子问题组成，该子问题可能买卖了0,1...k次 并不一定买卖的次数多 利润
     * 就越大 也可能前面几天一次不买 第j天买 i天卖
     * #理解：此问题的dp[i][k]中的k维 是确定的k次 而不是小于等于k次的最大利润 是等于k次的最大利润
     * 区别i i不是表示第i天一定卖出 而是i天的规模的最大利润 也就是说i不是确定值 是一个规模值
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        //买卖次数
        int K = 2;
        int length = prices.length;
        //dp 前i天购买k次的最大利润
        int[][] dp = new int[length + 1][K + 1];
        //initialize
        //第0天时(这里是指数组下标0 也就是第一天) 是没办法买入卖出 因此dp[0][k]都为0
        //购买0次时 是没有利润的 因此dp[i][0]都为0
        //状态转移方程：
        //   对于问题dp[i][k] 其子问题枚举为：
        //             第j天买入 第i天卖出 加上前j-1天(买入卖出1次 那么前j-1天的买入卖出次数为k-1)的最大利润：prices[i]-price[j]+dp[j-1][k-1]
        //             当然 我们要注意到 我们也可以这i天都不买入卖出 那么当前利润就为前j-1天的利润(购买次数为k 因为第i天没有卖出):dp[j-1][k]
        //             状态转移方程就为dp[i][k]=max(price[i]-price[j]+dp[j-1][k-1]...)
        //   简化计算次数：在子问题枚举时 j为变量 那么我们只用计算(price[j]-dp[j-1][k-1])的最小值 再用price[i]减去当前最小值
        //   简化表达式（j-1 i-1的简化）：
        //   （1）子问题枚举时 我们是枚举到i-1 也就是不可能有第j=i 第i天买入的情况 但是注意到 即使第i天买入第i天卖出：price[i]-price[i]+dp[i-1][k-1]
        //   可以看到对dp值是没有影响的（即使当前dp[i-1][k-1]是最大值 那表示的意思为第i天不卖出 前i-1天卖出k次的最大利润 这与我们最终要找最大利润是不冲突的）
        //   （2）子问题(price[i]-price[j]+dp[j-1][k-1])枚举时，子子问题为dp[j-1][k-1]<-------------------------
        for (int k = 1; k <= K; k++) {
            for (int i = 1; i <= length; i++) {
                //我们要找price[j]-dp[j-1][k-1]的最小值 那么初始值min为j=0时 price[0]-0=price[0]
                //####思考：对于初始点 从第0天买入 第i天卖出 price[i]-price[0] 只交易了1次
                // 而当k=2时 初始点任然是第0天买入 第i天卖出 那么如果k=2的dp[i][2]的状态转移的最终结果为：
                //          dp[i][2]=price[i]-price[0] 会对之后的子问题造成影响吗???????????
                // 同样的 当k=2 j=1时 dp[1-1][1]=0 也就是第0天是没有算买入卖出1次的 从第1天买入 第i天卖出 得到的结果也是一次买入卖出
                // 与k=2不对应 会有影响吗???????????
                int min = prices[0];
                for (int j = 1; j < i; j++) {
                    min = Math.min(prices[j] - dp[j][k - 1], min);
                }
                //还需要比较此情况： 第i天不卖出（也就是前i天已经卖了k次了）此比较是规模值比较 也就是说dp[i-1][k]的值可能是dp[i-2][k]的值..
                dp[i][k] = Math.max(dp[i - 1][k], prices[i - 1] - min);
            }
        }
        return Math.max(dp[length][1], dp[length][2]);
    }

    /**
     * 优化一：以上方法会出现超时情况 我们进一步分析我们的动态规划 是否存在重复计算的情况
     * 分析我们可以看到：
     * for (int j = 1; j < i; j++) {
     * min = Math.min(prices[j] - dp[j][k - 1], min);
     * }
     * 这一步，我们每一次子问题枚举时 前j-1次我们都已经计算过了的 我们只需要比较最后一次即可
     *
     * @param prices
     * @return
     */
    public int maxProfitOp(int[] prices) {
        //买卖次数
        int K = 2;
        int length = prices.length;
        //dp 前i天购买k次的最大利润
        int[][] dp = new int[length + 1][K + 1];
        for (int k = 1; k <= K; k++) {
            int min = prices[0];
            for (int i = 2; i <= length; i++) {
                min = Math.min(min, prices[i - 2] - dp[i - 2][k - 1]);
                //min = Math.min(prices[j] - dp[j][k - 1], min);
                dp[i][k] = Math.max(dp[i - 1][k], prices[i - 1] - min);
            }
        }
        return Math.max(dp[length][1], dp[length][2]);
    }

    /**
     * 空间复杂度的优化前的问题分析：
     * 从上面的双层for循环求解子问题我们看到 我们的二维dp横坐标为k=0 1 2 纵坐标是 i=0 1 2 ...
     * 我们是先计算完k=0的第一列 然后计算k=1的列的时候用到k=0的一列 计算k=2的列的时候 用到k=1的列
     * 由此我们可以看到dp我们只需要一维即可 每次内层循环结束 覆盖上一层即可
     * 思考：
     * 但是，我们还注意到，最外层k只有k=2 两次循环 我们能否把内层与外层循环交换一下顺序，
     * 这样内层dp只需要开辟常数空间（k=2）
     * 由此我们尝试交换内外层循环--------
     *
     * @param prices
     * @return
     */
    public int maxProfitChangeFor(int[] prices) {
        //买卖次数
        int K = 2;
        int length = prices.length;
        //dp 前i天购买k次的最大利润
        int[][] dp = new int[length + 1][K + 1];
        //每一层k的min初始值
        int[] min = {prices[0], prices[0]};
        for (int i = 2; i <= length; i++) {
            for (int k = 1; k <= K; k++) {
                //第一个i-2是数组下标 对应长度为i-1 第二个i-2是规模 前i-2个天
                min[k - 1] = Math.min(min[k - 1], prices[i - 2] - dp[i - 2][k - 1]);
                dp[i][k] = Math.max(dp[i - 1][k], prices[i - 1] - min[k - 1]);
            }
        }
        return Math.max(dp[length][1], dp[length][2]);
    }

    /**
     * 一维dp
     * 注意：由于k=2 内层循环时常数项 我们可以将内层循环直接展开写成两次dp[k]的更新 提高效率
     * 同时，二维数组的效率远不及一维数组的效率
     *
     * @param prices
     * @return
     */
    public int maxProfitOneDimensionDp(int[] prices) {
        //买卖次数
        int K = 2;
        int length = prices.length;
        //dp 前i天购买k次的最大利润
        //int[][] dp = new int[length + 1][K + 1];
        // 一维dp dp[0] 表示买卖0次 利润为0
        int[] dp = new int[K + 1];
        //每一层k的min初始值
        int[] min = {prices[0], prices[0]};
        for (int i = 2; i <= length; i++) {
            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!<---------注意此处应该先k=2 用到前一个还未更新的dp[1]的值 再更新dp[1]
            //原因：
            // 我们多加了前一天的最大收益，我们也可以改成加当前天的最大收益(price[i]-price[j]+dp[j-1][k-1])
            //
            //在第 j 天买入，收益就是 prices[i] - prices[j] + dp[j][k-1]
            //
            //不严谨的想一下，如果第 j 天就是最后我们要选择的买入点，它使得最后的收益最高，dp[j][k-1] 和 dp[j-1][k-1] 一定是相等的。
            // 因为第 j 天一定是一个低点而第 j - 1 天是个高点，第 j 天为了得到更高收益肯定选择不操作，
            // 所以和第 j - 1 天的收益是一样的，所以改了状态转移方程，最后求出的最高解还是一致的。
            //
            for (int k = 2; k >= 1; k--) {
                //第一个i-2是数组下标 对应长度为i-1 第二个i-2是规模 前i-2个天
                min[k - 1] = Math.min(min[k - 1], prices[i - 2] - dp[k - 1]);
                dp[k] = Math.max(dp[k], prices[i - 1] - min[k - 1]);
            }
        }
        return Math.max(dp[1], dp[2]);
    }

    /**
     * ------------------状态机方法------------
     * 状态机的理解：
     * （1）state：对于自动门 有两种状态 开门 关门
     * （2）event：事件 触发操作的条件，按下开关 开门或者关门 这里的“按下开关按钮”就是触发事件
     * （3）action：触发事件后，机器发生的动作，开门or关门
     * （4）transition：从一个状态变为另一个状态的过程，例如从开门状态转换为关门状态的过程
     * 以每一天为单位 每天都有四个状态 第一次买入股票 第一次卖出股票 第二次买入股票 第二次卖出股票
     *
     * @param prices
     * @return
     */
    public int maxProfitStateMachine(int[] prices) {
        //第一天状态机初始化 第一天买入股票 其他状态初始化为最小值
        int s1 = -prices[0], s2 = Integer.MIN_VALUE, s3 = Integer.MIN_VALUE, s4 = Integer.MIN_VALUE;
        int length = prices.length;
        for (int i = 1; i < length; i++) {
            //买入价格更低的股票 例如 5->2 ---->-5<-2
            s1 = Math.max(s1, -prices[i]);
            //卖出当前股票 或者 不操作
            s2 = Math.max(s2, s1 + prices[i]);
            //第二次买入股票 或者不操作
            s3 = Math.max(s3, s2 - prices[i]);
            //第二次卖出股票 或者不操作
            s4 = Math.max(s4, s3 + prices[i]);
        }
        return Math.max(0, s4);
    }

}
