package DynamicProgramming.NO486PredicttheWinner;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/14 14:09
 * @description --------------与博弈有关的动态规划 类似NO1690---------
 * 思考：引申的一个模型---------数组左端或者右端取走1，奇数偶数从外向里，会有单数双数的讨论，
 * 但是我们从里向外，就根本不需要讨论奇数偶数的问题
 * ------------------------------0和博弈-----------------------
 * 什么是零和博弈?
 * 无论采取何种策略，博弈双方的总得分不变。即一个人得分变多，另一个必然减少。
 * 在本题中，双方得分的总和即为 nums 的累加和。
 *
 * 在零和博弈中，让自己最优和让对手最差其实是相同的目标！
 * 原因还是那句话，两人的总得分不会变化，自己多了，对手必然减少。
 *
 * 那么不妨先让我们转变一下思路，把问题变为，先手如何让对手的最终得分最少
 * (对手最少就意味着自己的最终得分最大，我好啰嗦啊)。
 *
 * 另外，在零和博弈问题中，一般会存在先手优势和后手劣势：
 *
 * 先手优势：因为先手可以先走，所以可决定后手将要面对的局面。因此，后手虽然也会做出最优解，但是先手可以根据先发的优势，让后手进入最优解最差的局面。
 * 后手劣势：当先手走完后，虽然后手也会成为下一回合的先手，也可以做出最优解，但后手无法选择下一回合的局面。
 * 所以一般零和博弈问题的关键都是要找出如何让后手陷入更差的局面的方法。
 *
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().PredictTheWinner(new int[]{1, 5, 233, 7}));
    }

    public boolean PredictTheWinner(int[] nums) {
        int l = nums.length;
        int[] subSum = new int[l + 1];
        for (int i = 0; i < l; i++) {
            subSum[i + 1] = subSum[i] + nums[i];
        }
        //dp[i][j]表示当前[i,j]区间（子问题）能获得的最多分数
        int[][] dp = new int[l][l];
        //init
        for (int i = 0; i < l; i++) {
            dp[i][i] = nums[i];
        }
        //dp 本质上是以len=j-i+1作为子问题规模 最终len=l 到达最终问题
        for (int d = 1; d < l; d++) {
            for (int j = d; j < l; j++) {
                int i = j - d;
                int sum = subSum[j + 1] - subSum[i];
                dp[i][j] = Math.max(sum - dp[i + 1][j], sum - dp[i][j - 1]);
            }
        }
        return dp[0][l - 1] * 2 >= subSum[l];
    }

}
