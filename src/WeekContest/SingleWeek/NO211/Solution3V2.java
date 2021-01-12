package WeekContest.SingleWeek.NO211;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/19 16:56
 * @description
 */
public class Solution3V2 {

    public int bestTeamScore(int[] scores, int[] ages) {
        int count = ages.length;
        int bestScore = 0;
        Integer[] indexAfterSort = new Integer[count];
        for (int i = 0; i < count; i++) {
            indexAfterSort[i] = i;
        }
        //按照年龄大小排序 年龄大的根据分数从大到小排序
        Arrays.sort(indexAfterSort, (o1, o2) ->
                ages[o1] == ages[o2] ? scores[o1] - scores[o2] : ages[o1] - ages[o2]);
        //dp 一维dp表示选择当前队员的最大分数 既然当前队员可以选择 并且我们保证了年龄是从小到大排序（年龄相同分数以低到高排序）
        //那么当前队员选择就表示了前面的分数以及年龄都能够满足小于当前队员的分数的条件（当然同年龄时 当前队员是此刻得分最高的那位）
        int[] dp = new int[count];
        //initialize
        for (int i = 0; i < count; i++) {
            int curScore = scores[indexAfterSort[i]];
            dp[i] = curScore;
            //枚举j 只要当前i队员大于前面的某一个j队员的分数 就可以状态转移
            for (int j = 0; j < i; j++) {
                if (curScore >= scores[indexAfterSort[j]]) {
                    dp[i] = Math.max(dp[i], dp[j] + curScore);
                }
            }
            bestScore = Math.max(dp[i], bestScore);
        }
        return bestScore;
    }
}
