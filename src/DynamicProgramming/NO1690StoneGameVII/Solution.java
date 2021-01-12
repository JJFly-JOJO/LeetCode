package DynamicProgramming.NO1690StoneGameVII;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/14 11:32
 * @description -------------------与博弈有关的动态规划-----------
 * 思维：对于Alice和Bob两个玩家，我们只需要统一问题-------当前玩家与对手玩家
 * 并不需要分别关注Alice的选择策略与Bob的选择策略，本质上仅仅是当前选择方与下一次对手选择方，
 * 抽象问题后，我们只需要关注当前选择方如何选择让分差拉大
 */
public class Solution {

    public int stoneGameVII(int[] stones) {
        int l = stones.length;
        int[] subSum = new int[l + 1];
        for (int i = 1; i <= l; i++) {
            subSum[i] = subSum[i - 1] + stones[i - 1];
        }
        //dpSub[i][j]记录选择者能在区间[i,j]获得的总分
        //int[][] dpSub=new int[l][l];
        //dpRes[i][j]记录选择者能够与对方在区间[i,j]删除i或者j端点后得到的最大差值
        int[][] dpRes = new int[l][l];
        //init
        //当i==j dpRes[i][j]=0
        //当j-i=1
        /*for (int i = 1; i < l; i++) {+
            dpRes[i - 1][i] = Math.max(stones[i], stones[i - 1]);
        }*/
        //dp
        for (int d = 1; d < l; d++) {
            for (int i = d; i < l; i++) {
                int j = i - d;
                int v = subSum[i + 1] - subSum[j];
                dpRes[j][i] = Math.max(v - stones[j] - dpRes[j + 1][i], v - stones[i] - dpRes[j][i - 1]);
            }
        }
        return dpRes[0][l - 1];
    }

}
