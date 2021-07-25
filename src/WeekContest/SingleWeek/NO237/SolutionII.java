package WeekContest.SingleWeek.NO237;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/4/18 10:38
 * @description
 */
public class SolutionII {

    public int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        int count = 0;
        for (int c : costs) {
            coins -= c;
            if (coins >= 0) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

}
