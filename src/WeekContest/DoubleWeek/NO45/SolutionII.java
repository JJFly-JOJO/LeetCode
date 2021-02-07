package WeekContest.DoubleWeek.NO45;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/2/6 22:34
 * @description
 */
public class SolutionII {

    public int maxAbsoluteSum(int[] nums) {
        int max = 0;
        int cur = 0;
        for (int num : nums) {
            cur += num;
            if (cur <= 0) {
                cur = 0;
                continue;
            }
            max = Math.max(cur, max);
        }
        cur = 0;
        for (int num : nums) {
            cur += num;
            if (cur >= 0) {
                cur = 0;
                continue;
            }
            max = Math.max(max, -cur);
        }
        return max;
    }

}
