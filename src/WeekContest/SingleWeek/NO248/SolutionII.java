package WeekContest.SingleWeek.NO248;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/7/4 10:39
 * @description
 */
public class SolutionII {

    public int eliminateMaximum(int[] dist, int[] speed) {
        int[] arriveTime = new int[dist.length];
        for (int i = 0; i < dist.length; i++) {
            arriveTime[i] = (int) Math.ceil(dist[i] * 1.0d / speed[i]);
        }
        Arrays.sort(arriveTime);
        int ans = 0;
        for (int i = 0; i < arriveTime.length; i++) {
            if (ans >= arriveTime.length || i >= arriveTime[ans]) {
                break;
            }
            ans++;
        }
        return ans;
    }

}
