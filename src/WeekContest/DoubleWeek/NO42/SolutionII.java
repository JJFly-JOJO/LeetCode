package WeekContest.DoubleWeek.NO42;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/29 11:13
 * @description
 */
public class SolutionII {

    public double averageWaitingTime(int[][] customers) {
        double res = 0.0;
        int cur = customers[0][0];
        for (int[] customer : customers) {
            int t = cur < customer[0] ? customer[0] + customer[1] : cur + customer[1];
            res += (t - customer[0]);
            cur = t;
        }
        return res / customers.length;
    }

}
