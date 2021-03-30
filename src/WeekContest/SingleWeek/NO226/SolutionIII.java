package WeekContest.SingleWeek.NO226;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/31 11:17
 * @description
 */
public class SolutionIII {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new SolutionIII().canEat(new int[]{46, 5, 47, 48, 43, 34, 15, 26, 11, 25, 41, 47, 15, 25, 16, 50, 32, 42, 32, 21, 36, 34, 50, 45, 46, 15, 46, 38, 50, 12, 3, 26, 26, 16, 23, 1, 4, 48, 47, 32, 47, 16, 33, 23, 38, 2, 19, 50, 6, 19, 29, 3, 27, 12, 6, 22, 33, 28, 7, 10, 12, 8, 13, 24, 21, 38, 43, 26, 35, 18, 34, 3, 14, 48, 50, 34, 38, 4, 50, 26, 5, 35, 11, 2, 35, 9, 11, 31, 36, 20, 21, 37, 18, 34, 34, 10, 21, 8, 5
                },
                new int[][]{{85, 54, 42}, {4, 2, 4}, {2, 13, 1000000000}})));
    }

    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        long[] sub = new long[candiesCount.length + 1];
        for (int i = 0; i < candiesCount.length; i++) {
            sub[i + 1] = sub[i] + candiesCount[i];
        }
        boolean[] ans = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long min = queries[i][1];
            long max = queries[i][2] * (min + 1);
            long subMin = sub[queries[i][0]];
            long subMax = sub[queries[i][0] + 1];
            if (min < subMax && max > subMin) {
                ans[i] = true;
            }
        }
        return ans;
    }
}
