package WeekContest.DoubleWeek.NO44;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/1/23 22:18
 * @description
 */
public class SolutionI {

    public int largestAltitude(int[] gain) {
        int n = 0;
        int max = 0;
        for (int i : gain) {
            n += i;
            if (n > max) {
                max = n;
            }
        }
        return max;
    }

}
