package WeekContest.SingleWeek.NO238;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/4/25 10:42
 * @description
 */
public class SolutionI {

    public int sumBase(int n, int k) {
        int res = 0;
        while (n != 0) {
            res += n % k;
            n /= k;
        }
        return res;
    }

}
