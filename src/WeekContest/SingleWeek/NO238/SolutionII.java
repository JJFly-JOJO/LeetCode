package WeekContest.SingleWeek.NO238;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2021/4/25 10:44
 * @description
 */
public class SolutionII {

    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int res = 1;
        for (int i = 1; i < nums.length; ) {
            int j = i - 1;
            int idx = i;
            int count = 1;
            i++;
            while (i < nums.length && nums[i] == nums[i - 1]) {
                count++;
                i++;
            }
            int sum = 0;
            for (; j >= 0; j--) {
                sum += nums[idx] - nums[j];
                if (sum > k) {
                    break;
                }
                count++;
            }
            res = Math.max(res, count);
        }
        return res;
    }

}
