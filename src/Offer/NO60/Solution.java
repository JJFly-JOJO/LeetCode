package Offer.NO60;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/4/14 10:31
 * @description
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().dicesProbability(2)));
    }

    public double[] dicesProbability(int n) {
        //n大小限制在11
        //dp记录n个骰子下得到k点的组合次数 最后除以总组合次数得到概率
        int[][] dp = new int[n][70];
        //initialize
        for (int i = 1; i <= 6; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < n; i++) {
            //缩小遍历范围
            int max = (i + 1) * 6;
            for (int j = i + 1; j <= max; j++) {
                for (int k = 1; k <= 6; k++) {
                    if (j - k <= 0) {
                        break;
                    }
                    dp[i][j] += dp[i - 1][j - k];
                }
            }
        }
        double mod = Math.pow(6.0, n);
        double[] res = new double[6 * n - n + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = dp[n - 1][i + n] / mod;
        }
        return res;
    }

}
