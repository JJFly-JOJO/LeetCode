package WeekContest.SingleWeek.NO214;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/11/8 10:29
 * @description
 */
public class Solution1 {

    public int getMaximumGenerated(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[] num = new int[n + 1];
        boolean[] mark = new boolean[n + 1];
        num[0] = 0;
        num[1] = 1;
        mark[0] = true;
        mark[1] = true;
        int res = 1;
        for (int i = 1; i <= n; i++) {
            int d = 2 * i;
            if (d <= n && !mark[d]) {
                mark[d] = true;
                num[d] = num[i];
                res = Math.max(res, num[d]);
            }
            if (d + 1 <= n && !mark[d + 1]) {
                mark[d + 1] = true;
                num[d + 1] = num[i] + num[i + 1];
                res = Math.max(res, num[d + 1]);
            }
        }
        return res;
    }

}
