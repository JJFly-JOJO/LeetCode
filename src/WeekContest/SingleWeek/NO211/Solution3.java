package WeekContest.SingleWeek.NO211;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/18 10:59
 * @description 输入：scores = [4,5,6,5], ages = [2,1,2,1]
 * 输出：16
 * 解释：最佳的选择是后 3 名球员。注意，你可以选中多个同龄球员。
 * 输入：scores = [1,2,3,5], ages = [8,9,10,1]
 * 输出：6
 * 解释：最佳的选择是前 3 名球员。
 */
public class Solution3 {

    public static void main(String[] args) {
        System.out.println(new Solution3().bestTeamScore(new int[]{1, 2, 3, 5}, new int[]{8, 9, 10, 1}));
    }

    public int bestTeamScore(int[] scores, int[] ages) {
        int count = ages.length;
        Integer[] indexAfterSort = new Integer[count];
        for (int i = 0; i < count; i++) {
            indexAfterSort[i] = i;
        }
        //按照年龄大小排序 年龄大的根据分数从大到小排序
        /*Arrays.sort(indexAfterSort,(o1, o2)->{
            ages[o1]==ages[o2]?scores[o1]-scores[o2]:ages[o1]-ages[o2];
        });*/
        Arrays.sort(indexAfterSort, (o1, o2) -> ages[o1] == ages[o2] ? scores[o1] - scores[o2] : ages[o1] - ages[o2]);
        //dp 当前队伍规模对应的最大分数 当前第i个人不选与选对应的最大分数
        int[][] dp = new int[count + 1][2];
        //initialize
        dp[1][1] = scores[indexAfterSort[0]];
        for (int i = 2; i <= count; i++) {
            //当前人选
            int temp = 0;
            if (scores[indexAfterSort[i - 1]] >= scores[indexAfterSort[i - 2]]) {
                dp[i][1] = Math.max(dp[i - 1][1] + scores[indexAfterSort[i - 1]], dp[i - 1][0] + scores[indexAfterSort[i - 1]]);
            } else {
                dp[i][1] = Math.max(getSum(indexAfterSort, scores, i - 1), temp);
            }

            //当前人不选
            dp[i][0] = Math.max(dp[i - 1][1], dp[i - 1][0]);
        }
        return Math.max(dp[count][0], dp[count][1]);
    }

    private int getSum(Integer[] indexAfterSort, int[] scores, int curIndex) {
        int sum = 0;
        for (int j = 0; j <= curIndex; j++) {
            if (scores[indexAfterSort[curIndex]] >= scores[indexAfterSort[j]]) {
                sum += scores[indexAfterSort[j]];
            }
        }
        return sum;
    }

    private int findMinIndex(Integer[] indexAfterSort, int[] scores, int[] ages, int curIndex) {
        int i = curIndex - 1;
        for (; i >= 0; i--) {
            if (ages[curIndex] == ages[indexAfterSort[i]]) {
                continue;
            }
            if (scores[indexAfterSort[i]] <= scores[curIndex]) {
                break;
            }
        }
        return i;
    }

}
