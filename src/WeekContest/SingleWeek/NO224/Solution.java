package WeekContest.SingleWeek.NO224;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/17 10:30
 * @description
 */
public class Solution {

    public int countGoodRectangles(int[][] rectangles) {
        int[] r = new int[rectangles.length];
        int max = 0;
        for (int i = 0; i < r.length; i++) {
            r[i] = Math.min(rectangles[i][0], rectangles[i][1]);
            max = Math.max(r[i], max);
        }
        int res = 0;
        for (int v : r) {
            if (v == max) {
                res++;
            }
        }
        return res;
    }

}
