package WeekContest.SingleWeek.NO239;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/5/2 10:37
 * @description
 */
public class SolutionII {

    public int getMinDistance(int[] nums, int target, int start) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                int val = Math.abs(i - start);
                if (val < min) {
                    min = val;
                }
            }
        }
        return min;
    }

}
