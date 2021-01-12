package WeekContest.DoubleWeek.NO39;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/15 21:35
 * @description  ------------动态规划 求解存在问题--------
 */
public class SolutionIII {

    public static void main(String[] args) {
        System.out.println(new SolutionIII().minimumJumps(
                new int[]{128, 178, 147, 165, 63, 11, 150, 20, 158, 144, 136}, 61, 170, 135));
    }

    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        int[] dp = new int[x + b];
        Arrays.fill(dp, 10000);
        //initialize
        dp[0] = 0;
        Set<Integer> f = new HashSet<>();
        for (int i : forbidden) {
            f.add(i);
        }
        for (int i = 1; i <= x; i++) {
            //一次前跳
            int t1 = 10000;
            int temp = i - a;
            if (temp >= 0) {
                if (!f.contains(temp) && dp[temp] < 10000) {
                    t1 = dp[temp] + 1;
                }
            }
            //后跳 while循环 一直找达到dp存在值的点
            int t2 = 10000;
            temp = i + b - a;
            //v 记录减a的次数
            int v = 1;
            while (temp >= 0) {
                if (!f.contains(temp) && dp[temp] < 10000) {
                    t2 = dp[temp] + v + 1;
                    break;
                }
                temp -= a;
                v++;
            }
            dp[i] = Math.min(t1, t2);
        }
        return dp[x] >= 10000 ? -1 : dp[x];
    }

}
