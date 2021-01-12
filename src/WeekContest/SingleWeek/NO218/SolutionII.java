package WeekContest.SingleWeek.NO218;

import java.util.Arrays;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/12/6 10:40
 * @description
 */
public class SolutionII {

    public int maxOperations(int[] nums, int k) {
        Arrays.sort(nums);
        int l = 0;
        int r = nums.length - 1;
        int res = 0;
        while (l < r) {
            int v = nums[l] + nums[r];
            if (v == k) {
                res++;
                l++;
                r--;
            } else if (v < k) {
                l++;
            } else {
                r--;
            }
        }
        return res;
    }

}
